package com.ryulth.springlab.event

interface DomainEvent <ID> {
    fun getId(): ID
}