package com.jayesh.readmedia.post

import com.jayesh.readmedia.book.BookDto
import java.io.Serializable

/**
 * A DTO for the {@link com.jayesh.readmedia.post.Post} entity
 */
data class PostDto(
    val id: Long? = null,
    val articleTitle: String? = null,
    val shortDescription: String? = null,
    val summary: String? = null,
    val genreTags: Array<String>? = null,
    val book: BookDto? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostDto

        if (id != other.id) return false
        if (articleTitle != other.articleTitle) return false
        if (shortDescription != other.shortDescription) return false
        if (summary != other.summary) return false
        if (genreTags != null) {
            if (other.genreTags == null) return false
            if (!genreTags.contentEquals(other.genreTags)) return false
        } else if (other.genreTags != null) return false
        if (book != other.book) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (articleTitle?.hashCode() ?: 0)
        result = 31 * result + (shortDescription?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        result = 31 * result + (genreTags?.contentHashCode() ?: 0)
        result = 31 * result + (book?.hashCode() ?: 0)
        return result
    }
}