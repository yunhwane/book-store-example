package com.example.bookstore.book.application.port.`in`

import com.example.bookstore.book.domain.Category

data class UpdateBookCommand(
    val title: String,
    val content: String,
    val category: Category,
    val author: String
){
}