package com.example.bookstore.book.application.port.out

import com.example.bookstore.book.domain.Book

interface SaveBookPort {
    fun save(book: Book): Book
}