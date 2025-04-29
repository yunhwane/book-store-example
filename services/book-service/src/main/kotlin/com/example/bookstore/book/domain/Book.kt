package com.example.bookstore.book.domain

import java.time.LocalDateTime


data class Book(
    val bookId: Long? = null,
    val title: String,
    val content: String,
    val category: Category,
    val author: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
