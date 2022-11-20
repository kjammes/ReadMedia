package com.jayesh.readmedia.post

import org.springframework.data.repository.PagingAndSortingRepository

interface PostsRepo : PagingAndSortingRepository<Post, Long> {
}