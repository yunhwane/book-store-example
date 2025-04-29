package com.example.bookstore.book.application.service

import com.example.bookstore.book.application.port.`in`.SaveBookCommand
import com.example.bookstore.book.application.port.`in`.SaveBookUseCase
import com.example.bookstore.book.application.port.out.SaveBookPort
import com.example.bookstore.book.domain.Book
import org.springframework.stereotype.Service

@Service
class SaveBookService(
    private val saveBookPort: SaveBookPort
) : SaveBookUseCase {

    override fun execute(command: SaveBookCommand): Book {
        val book = Book(
            title = command.title,
            content = command.content,
            category = command.category,
            author = command.author
        )

        return saveBookPort.save(book)
    }

}