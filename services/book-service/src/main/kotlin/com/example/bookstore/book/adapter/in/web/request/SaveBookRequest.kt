package com.example.bookstore.book.adapter.`in`.web.request

import com.example.bookstore.book.domain.Category

data class SaveBookRequest(
    val title: String,
    val content: String,
    val category: Category,
    val author: String
) {
}