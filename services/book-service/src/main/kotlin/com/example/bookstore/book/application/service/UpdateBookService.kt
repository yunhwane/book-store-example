package com.example.bookstore.book.application.service

import com.example.bookstore.book.application.port.`in`.UpdateBookCommand
import com.example.bookstore.book.application.port.`in`.UpdateBookUseCase
import com.example.bookstore.book.application.port.out.LoadBookPort
import com.example.bookstore.book.application.port.out.SaveBookPort
import com.example.bookstore.book.domain.Book
import org.springframework.stereotype.Service


@Service
class UpdateBookService(
    private val loadBookPort: LoadBookPort,
    private val saveBookPort: SaveBookPort,
) : UpdateBookUseCase{

    override fun execute(bookId: Long, command: UpdateBookCommand, ): Book {
        val book = loadBookPort.getBook(bookId)
        book.update(title = command.title, content = command.content, category = command.category, author = command.author)
        return saveBookPort.save(book)
    }
}