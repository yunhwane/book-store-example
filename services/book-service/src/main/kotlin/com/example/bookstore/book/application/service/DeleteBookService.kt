package com.example.bookstore.book.application.service

import com.example.bookstore.book.application.port.`in`.DeleteBookUseCase
import com.example.bookstore.book.application.port.out.DeleteBookPort
import com.example.bookstore.book.application.port.out.LoadBookPort
import org.springframework.stereotype.Service


@Service
class DeleteBookService(
    private val deleteBookPort: DeleteBookPort
) : DeleteBookUseCase {

    override fun execute(bookId: Long) {
        deleteBookPort.delete(bookId)
    }
}