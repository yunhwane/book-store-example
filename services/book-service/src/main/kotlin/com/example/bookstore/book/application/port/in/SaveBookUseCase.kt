package com.example.bookstore.book.application.port.`in`

import com.example.bookstore.book.domain.Book


interface SaveBookUseCase {
    fun execute(command: SaveBookCommand): Book
}