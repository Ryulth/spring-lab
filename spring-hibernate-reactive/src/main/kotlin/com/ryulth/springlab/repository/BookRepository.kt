package com.ryulth.springlab.repository

import com.ryulth.springlab.model.Book
import org.springframework.stereotype.Repository

@Repository
interface BookRepository {
    suspend fun save(book: Book): Book
    suspend fun findById(bookId: Long): Book?
    suspend fun deleteById(bookId: Long)
}