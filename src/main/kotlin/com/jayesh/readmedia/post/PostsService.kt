package com.jayesh.readmedia.post

import com.jayesh.readmedia.exceptions.FetchFailureException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PostsService
@Autowired
constructor(
    private val postsRepo: PostsRepo,
    private val LOG : Logger = LoggerFactory.getLogger("PostsService")
) : IPostsService {

    override fun findAllPaginated(skip: Int, limit: Int): Page<Post> {
        try {
            return postsRepo.findAll(PageRequest.of(skip, limit))
        } catch (e: Exception) {
            val message = "Unable to fetch list of posts. ${e.localizedMessage}"
            LOG.error(message)
            throw FetchFailureException(message)
        }
    }

    override fun savePost(post: Post): Boolean {
        return try {
            postsRepo.save(post)
            true
        } catch (e: Exception) {
            val message = "Exception encountered while trying to save post. ${e.localizedMessage}"
            LOG.error(message)
            throw RuntimeException(message)
        }
    }
}