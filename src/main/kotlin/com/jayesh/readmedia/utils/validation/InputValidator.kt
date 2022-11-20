package com.jayesh.readmedia.utils.validation

import com.jayesh.readmedia.post.PostDto

class InputValidator {
    fun isValidPostDto(postDto: PostDto): StringBuilder {
        var result = StringBuilder("Following are missing parameters from input request body : ")
        if (postDto.articleTitle == null)
            result.append("- Article Title. ")
        if (postDto.genreTags.isNullOrEmpty())
            result.append("- Genre Tags. ")
        if (postDto.shortDescription == null)
            result.append("- Short Description Of Book Summary. ")
        if (postDto.summary == null)
            result.append("- Summary of book(Main content). ")
        if (postDto.book != null) {
            result.append("\nSince book info is also passed, following are the missing input params for book : ")
            val bookDto = postDto.book
            if (bookDto.author == null)
                result.append("- Author of the book. ")
            if (bookDto.title == null)
                result.append("- Title of the book. ")
            if (bookDto.publishDate == null)
                result.append("- Publish date of the book. ")
        }

        result = StringBuilder("")
        return result
    }
}