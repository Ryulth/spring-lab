package com.ryulth.springlab.service

import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.event.DomainEventPublisher
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.model.BookCreated
import com.ryulth.springlab.model.BookDeleted
import com.ryulth.springlab.model.BookEvent
import com.ryulth.springlab.model.BookUpdated
import com.ryulth.springlab.repository.BookRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val domainEventPublisher: DomainEventPublisher
) {
    suspend fun create(request: BookRequest): Book =
        bookRepository.save(
            Book(
                title = request.title,
                authorId = request.authorId
            )
        ).also {
            publishBookEvent(BookCreated.of(it))
        }

    suspend fun get(bookId: Long): Book = bookRepository.findById(bookId)
        ?: throw IllegalArgumentException("Book is null $bookId")

    suspend fun update(bookId: Long, request: BookRequest): Book =
        bookRepository.save(get(bookId).update(request.title, request.authorId))
            .also { publishBookEvent(BookUpdated.of(it)) }

    suspend fun delete(bookId: Long) =
        bookRepository.deleteById(bookId)
            .also { publishBookEvent(BookDeleted.of(bookId)) }

    private suspend fun publishBookEvent(event: BookEvent) = domainEventPublisher.publish(event)

    private fun <T : Any> Optional<T>.toNullable(): T? = this.orElse(null)
}
