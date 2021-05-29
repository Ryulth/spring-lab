package com.ryulth.springlab.webapi

import com.ryulth.springlab.Application
import com.ryulth.springlab.dto.BookRequest
import com.ryulth.springlab.model.Book
import com.ryulth.springlab.repository.BookRepository
import kotlinx.coroutines.reactive.awaitFirst
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class BookControllerTest {

    @LocalServerPort
    private val port = 0

    @Test
    fun testCreateBook() {
        val title = "title"
        val authorId = 1L
        val request = BookRequest(
            title = title,
            authorId = authorId
        )
        val webClient = WebClient.create("http://localhost:$port")
        val book = webClient.post()
            .uri("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(Book::class.java)
            .block()
//            .awaitFirst()
        Assertions.assertNotNull(book)
    }

//    @Test
//    fun testCreateBook() {
//        val title = "title"
//        val authorId = 1L
//        val request = BookRequest(
//            title = title,
//            authorId = authorId
//        )
//        Mockito.`when`(repository.save(employee)).thenReturn(Mono.just(employee))
//        webClient.post()
//            .uri("/create")
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(BodyInserters.fromObject<Any>(employee))
//            .exchange()
//            .expectStatus().isCreated
//        Mockito.verify(repository, times(1)).save(employee)
//    }
}