package com.jayesh.readmedia.utils

import com.jayesh.readmedia.book.Book
import com.jayesh.readmedia.book.BookDto
import com.jayesh.readmedia.post.Post
import com.jayesh.readmedia.post.PostDto

class ConversionUtil {
    fun convertToPost(postDto: PostDto) : Post {
        val post = Post()

        post.articleTitle = postDto.articleTitle
        post.genreTags = postDto.genreTags
        post.summary = postDto.summary
        post.shortDescription = postDto.shortDescription
        post.book = convertToBook(postDto.book)

        return post
    }

    private fun convertToBook(bookDto: BookDto?) : Book {
        val book = Book()
        book.author = bookDto?.author
        book.title = bookDto?.title
        book.lastUpdateDate = bookDto?.lastUpdateDate
        book.publishDate = bookDto?.publishDate
        return book
    }
}