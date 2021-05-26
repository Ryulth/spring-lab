package com.ryulth.springlab.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
