package com.ryulth.springlab.service

import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@Service
class BookService(
    private val bookRepository: BookRepository,
) {
    @Transactional
    suspend fun create(request: BookRequest): Book =
        bookRepository.save(
            Book(
                title = request.title,
                authorId = request.authorId
            )
        ).also { if (Random.nextBoolean()) throw RuntimeException() }

    @Transactional
    suspend fun get(bookId: Long): Book = bookRepository.findById(bookId)
        ?: throw IllegalArgumentException("Book is null $bookId")

    @Transactional
    suspend fun update(bookId: Long, request: BookRequest): Book =
        bookRepository.save(get(bookId).update(request.title, request.authorId))

    @Transactional
    suspend fun delete(bookId: Long) =
        bookRepository.deleteById(bookId)
}
