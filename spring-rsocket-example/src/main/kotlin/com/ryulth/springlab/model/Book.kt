package com.ryulth.springlab.model

class Book(
    var id: Long? = null,
    var title: String,
    var authorId: Long
) {
    fun update(title: String, authorId: Long): Book {
        this.title = title
        this.authorId = authorId
        return this
    }
}
