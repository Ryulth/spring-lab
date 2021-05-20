package com.ryulth.springlab.repository

import com.ryulth.springlab.model.Book
import mu.KLogging
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.stereotype.Component

@Component
class BookRepositoryImpl(
    private val sessionFactory: Mutiny.SessionFactory
) : BookRepository {
    override suspend fun save(book: Book): Book {
        return book.id?.let {
            sessionFactory.withTransaction { session, transaction ->
                logger.info { "save transaction $transaction" }
                session.persist(book)
            }.subscribeAsCompletionStage().join()
            book
        } ?: sessionFactory.withTransaction { session, transaction ->
            logger.info { "save transaction $transaction" }
            session.merge(book)
        }.subscribeAsCompletionStage().join()
    }

    override suspend fun findById(bookId: Long): Book? {
        return sessionFactory.withTransaction { session, transaction ->
            logger.info { "findById transaction $transaction" }
            session.find(Book::class.java, bookId)
        }.subscribeAsCompletionStage().join()
    }

    override suspend fun deleteById(bookId: Long) {
        sessionFactory.withTransaction { session, transaction ->
            logger.info { "deleteById transaction $transaction" }
            session.find(Book::class.java, bookId).call { book ->
                session.remove(book)
            }
        }.subscribeAsCompletionStage().join()
    }

    companion object : KLogging()
}
