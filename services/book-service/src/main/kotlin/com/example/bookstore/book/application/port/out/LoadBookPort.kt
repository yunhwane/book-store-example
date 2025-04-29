package com.example.bookstore.book.application.port.out

import com.example.bookstore.book.domain.Book

interface LoadBookPort {
    fun getBook(bookId: Long): Book
}