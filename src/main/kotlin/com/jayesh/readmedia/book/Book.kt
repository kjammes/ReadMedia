package com.jayesh.readmedia.book

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "book")
open class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq")
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    open var title: String? = null
    open var author: String? = null
    open var publishDate: LocalDate? = null
    open var lastUpdateDate: LocalDate? = null
}