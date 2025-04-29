package com.example.bookstore.book.application.port.out

import com.example.bookstore.book.domain.Book

interface LoadBookPort {
    fun getBook(bookId: Long): Book
    fun getBooks(limit: Long, offset: Long): List<Book>
    fun count(limit: Long): Long
}