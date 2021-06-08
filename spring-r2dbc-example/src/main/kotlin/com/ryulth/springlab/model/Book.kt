package com.ryulth.springlab.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("Book")
data class Book(
    @Id
    val id: Long? = null,
    var title: String,
    var authorId: Long
) {
    fun update(title: String, authorId: Long): Book {
        this.title = title
        this.authorId = authorId
        return this
    }
}
