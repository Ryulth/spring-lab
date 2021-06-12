package com.ryulth.springlab.webapi

import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.service.BookService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mu.KLogging
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller

@Controller
class BookController(
    private val bookService: BookService
) {
    // fire-and-forget
    @MessageMapping("books.create")
    suspend fun createBook(@Payload request: BookRequest): Unit {
        CoroutineScope(Dispatchers.IO).launch {
            logger.info { "Received fire-and-forget request: $request" }
            bookService.create(request)
        }
    }

    // request-response
    @MessageMapping("books.findByBookId.{bookId}")
    suspend fun getBook(@DestinationVariable("bookId") bookId: Long): Book = bookService.get(bookId)

    // request-response
    @MessageMapping("books.update.{bookId}")
    suspend fun updateBook(@DestinationVariable("bookId") bookId: Long, @Payload request: BookRequest): Book =
        bookService.get(bookId)

    // fire-and-forget
    @MessageMapping("books.delete.{bookId}")
    suspend fun deleteBook(@DestinationVariable("bookId") bookId: Long): Unit {
        CoroutineScope(Dispatchers.IO).launch {
            bookService.delete(bookId)
        }
    }

    companion object : KLogging()
}
