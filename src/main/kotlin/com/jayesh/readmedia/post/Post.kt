package com.jayesh.readmedia.post

import com.jayesh.readmedia.book.Book
import com.vladmihalcea.hibernate.type.array.StringArrayType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.*

@TypeDefs(
    TypeDef(name = "string-array", typeClass = StringArrayType::class)
)
@Entity
@Table(name = "post")
open class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq")
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    open var articleTitle: String? = null
    open var shortDescription: String? = null
    open var summary: String? = null
    @Type(type = "string-array")
    @Column(name = "genreTags", columnDefinition = "text[]")
    open var genreTags: Array<String>? = null

    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "book_id")
    open var book: Book? = null
}