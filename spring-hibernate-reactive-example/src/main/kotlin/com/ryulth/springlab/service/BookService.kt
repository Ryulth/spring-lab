package com.ryulth.springlab.service

import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
) {
    suspend fun create(request: BookRequest): Book =
        bookRepository.save(
            Book(
                title = request.title,
                authorId = request.authorId
            )
        )

    suspend fun get(bookId: Long): Book = bookRepository.findById(bookId)
        ?: throw IllegalArgumentException("Book is null $bookId")

    suspend fun update(bookId: Long, request: BookRequest): Book =
        bookRepository.save(get(bookId).update(request.title, request.authorId))

    suspend fun delete(bookId: Long) =
        bookRepository.deleteById(bookId)
}
