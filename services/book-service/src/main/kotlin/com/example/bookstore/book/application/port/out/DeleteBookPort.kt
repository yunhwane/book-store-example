package com.example.bookstore.book.application.port.out

import com.example.bookstore.book.domain.Book

interface DeleteBookPort {
    fun delete(bookId: Long)
}