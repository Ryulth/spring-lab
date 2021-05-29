package com.ryulth.springlab.model

import com.ryulth.springlab.event.DomainEvent

sealed class BookEvent(
    private val id: Long
) : DomainEvent<Long> {
    override fun getId(): Long {
        return id
    }
}

class BookCreated private constructor(
    private val entity: Book
) : BookEvent(entity.id!!) {
    companion object {
        fun of(book: Book): BookCreated = BookCreated(book)
    }
    fun getEntity() = entity
}

class BookUpdated private constructor(
    private val entity: Book
) : BookEvent(entity.id!!) {
    companion object {
        fun of(book: Book): BookUpdated = BookUpdated(book)
    }
    fun getEntity() = entity
}

class BookDeleted private constructor(
    bookId: Long
) : BookEvent(bookId) {
    companion object {
        fun of(bookId: Long): BookDeleted = BookDeleted(bookId)
    }
}
