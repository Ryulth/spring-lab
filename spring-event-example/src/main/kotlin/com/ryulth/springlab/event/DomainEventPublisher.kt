package com.ryulth.springlab.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DomainEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    suspend fun <ID> publish(event: DomainEvent<ID>) = this.applicationEventPublisher.publishEvent(event)

    suspend fun <ID> publish(events: Collection<DomainEvent<ID>>) = events.forEach { event -> this.publish(event) }
}