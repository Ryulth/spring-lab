package com.ryulth.springlab.webapi

import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService
) {
    @PostMapping
    suspend fun createBook(@RequestBody request: BookRequest): Book = bookService.create(request)

    @GetMapping("/{bookId}")
    suspend fun getBook(@PathVariable bookId: Long): Book = bookService.get(bookId)

    @PutMapping("/{bookId}")
    suspend fun updateBook(@PathVariable bookId: Long, @RequestBody request: BookRequest): Book =
        bookService.update(bookId, request)

    @DeleteMapping("/{bookId}")
    suspend fun deleteBook(@PathVariable bookId: Long) = bookService.delete(bookId)
}