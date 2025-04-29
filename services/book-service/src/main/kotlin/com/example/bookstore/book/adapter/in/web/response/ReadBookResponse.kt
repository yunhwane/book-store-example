package com.example.bookstore.book.adapter.`in`.web.response

import com.example.bookstore.book.domain.Category
import java.time.LocalDateTime

data class ReadBookResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: Category,
    val author: String,
    val createdAt: LocalDateTime?= null,
    val updatedAt: LocalDateTime?= null,
) {
}