package com.ryulth.springlab.repository

import com.ryulth.springlab.model.Book
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface BookRepository: CoroutineCrudRepository<Book, Long>
