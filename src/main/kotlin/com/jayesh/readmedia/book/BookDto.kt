package com.jayesh.readmedia.book

import java.io.Serializable
import java.time.LocalDate

/**
 * A DTO for the {@link com.jayesh.readmedia.book.Book} entity
 */
data class BookDto(
    var id: Long? = null,
    var title: String? = null,
    var author: String? = null,
    var publishDate: LocalDate? = null,
    var lastUpdateDate: LocalDate? = null
) : Serializable