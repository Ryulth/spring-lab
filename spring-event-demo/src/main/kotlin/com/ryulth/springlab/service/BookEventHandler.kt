package com.ryulth.springlab.service

import com.ryulth.springlab.model.BookCreated
import com.ryulth.springlab.model.BookDeleted
import com.ryulth.springlab.model.BookUpdated
import mu.KLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class BookEventHandler {

    @EventListener
    fun onBookCreated(event: BookCreated) {
        val book = event.getEntity()
        logger.info { "BookCreated event handle $book" }
    }

    @EventListener
    fun onBookUpdated(event: BookUpdated) {
        val book = event.getEntity()
        logger.info { "BookUpdated event handle $book" }
    }

    @EventListener
    fun onBookDeleted(event: BookDeleted) {
        val bookId = event.getId()
        logger.info { "BookDeleted event handle $bookId" }
    }

    companion object : KLogging()
}