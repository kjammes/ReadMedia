package com.jayesh.readmedia.post

import org.springframework.data.domain.Page

interface IPostsService {
    fun findAllPaginated(skip: Int, limit: Int) : Page<Post>
    fun savePost(post: Post) : Boolean
}