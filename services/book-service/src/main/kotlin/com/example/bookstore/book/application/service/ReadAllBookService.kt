package com.example.bookstore.book.application.service

import com.example.bookstore.book.application.port.`in`.ReadAllBookUseCase
import com.example.bookstore.book.application.port.out.LoadBookPort
import com.example.bookstore.book.domain.Meta
import com.example.bookstore.book.domain.ReadAllBooksPageResult
import org.springframework.stereotype.Service


@Service
class ReadAllBookService(
    private val loadBookPort: LoadBookPort
) : ReadAllBookUseCase{

    override fun execute(page: Long, pageSize: Long): ReadAllBooksPageResult {
        return ReadAllBooksPageResult(
            meta = Meta(
                bookCount = loadBookPort.count(
                    PageLimitCalculator.calculatePageLimit(page, pageSize, 10L)
                )
            ),
            book = loadBookPort.getBooks(
                (page - 1) * pageSize,
                pageSize
            )
        )
    }
}