package com.example.bookstore.book.application.service

import com.example.bookstore.book.application.port.`in`.ReadBookUseCase
import com.example.bookstore.book.application.port.out.LoadBookPort
import com.example.bookstore.book.domain.Book
import org.springframework.stereotype.Service


@Service
class ReadBookService(
    private val loadBookPort: LoadBookPort
) : ReadBookUseCase{

    override fun execute(bookId: Long): Book {
        return loadBookPort.getBook(bookId)
    }
}