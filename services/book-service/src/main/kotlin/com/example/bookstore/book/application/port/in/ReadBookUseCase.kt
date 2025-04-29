package com.example.bookstore.book.application.port.`in`

import com.example.bookstore.book.domain.Book

interface ReadBookUseCase {

    fun execute(bookId: Long): Book
}