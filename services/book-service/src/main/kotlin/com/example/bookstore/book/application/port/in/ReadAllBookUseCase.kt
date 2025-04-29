package com.example.bookstore.book.application.port.`in`

import com.example.bookstore.book.domain.ReadAllBooksPageResult


interface ReadAllBookUseCase {
    fun execute(page: Long, pageSize: Long): ReadAllBooksPageResult
}