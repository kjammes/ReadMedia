package com.jayesh.readmedia.post

import com.jayesh.readmedia.exceptions.FetchFailureException
import com.jayesh.readmedia.utils.StringAndLogUtils
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
    private val LOG : Logger = LoggerFactory.getLogger("PostsService"),
    private val stringAndLogUtils: StringAndLogUtils = StringAndLogUtils(LOG)
) : IPostsService {

    override fun findAllPaginated(skip: Int, limit: Int): Page<Post> {
        try {
            return postsRepo.findAll(PageRequest.of(skip, limit))
        } catch (e: Exception) {
            val message = stringAndLogUtils.buildErrorMessageAndLog("Unable to fetch list of posts", e)
            throw FetchFailureException(message)
        }
    }

    override fun savePost(post: Post): Boolean {
        return try {
            postsRepo.save(post)
            true
        } catch (e: Exception) {
            val message = stringAndLogUtils.buildErrorMessageAndLog("Exception encountered while trying to save post.", e)
            throw RuntimeException(message)
        }
    }

    override fun deletePost(id: Long): String {
        var responseMessage : String
        val exists: Boolean

        try {
            exists = postsRepo.existsById(id)
        } catch (e : Exception) {
            responseMessage = stringAndLogUtils.buildErrorMessageAndLog("Exception occurred while checking if Post with $id exists.", e)
            return responseMessage
        }

        try {
            if (exists) {
                postsRepo.deleteById(id)
                responseMessage = "Successfully deleted Post with id : $id"
                LOG.info(responseMessage)
            } else {
                responseMessage = "Post with id : $id does not exist. Please pass valid id."
            }
        } catch (e : Exception) {
            responseMessage = stringAndLogUtils.buildErrorMessageAndLog("Exception encountered while deleting Post with id : $id.", e)
        }

        return responseMessage
    }
}