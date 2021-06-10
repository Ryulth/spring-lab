package com.ryulth.springlab.repository

import com.ryulth.springlab.model.Book
import org.springframework.stereotype.Component

@Component
class BookRepositoryImpl : BookRepository {
    override suspend fun save(book: Book): Book {
        book.id ?: run {
            book.id = maxId++
        }
        database[book.id!!] = book
        return book
    }

    override suspend fun findById(bookId: Long): Book? {
        return database[bookId]
    }

    override suspend fun deleteById(bookId: Long) {
        database.remove(bookId)
    }

    companion object {
        private val database = mutableMapOf<Long, Book>()
        private var maxId = 1L
    }
}