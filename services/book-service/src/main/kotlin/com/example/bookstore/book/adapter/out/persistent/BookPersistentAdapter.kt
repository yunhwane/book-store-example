package com.example.bookstore.book.adapter.out.persistent

import Snowflake
import com.example.bookstore.book.adapter.out.persistent.entity.BookEntity
import com.example.bookstore.book.adapter.out.persistent.repository.JpaBookRepository
import com.example.bookstore.book.application.port.out.DeleteBookPort
import com.example.bookstore.book.application.port.out.LoadBookPort
import com.example.bookstore.book.application.port.out.SaveBookPort
import com.example.bookstore.book.domain.Book
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class BookPersistentAdapter(
    private val jpaBookRepository: JpaBookRepository,
) : SaveBookPort, LoadBookPort, DeleteBookPort {

    private val snowflake = Snowflake()

    override fun save(book: Book): Book {
        val bookEntity = BookEntity(
            id = snowflake.nextId(),
            title = book.title,
            content = book.content,
            category = book.category,
            author = book.author,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val savedBookEntity = jpaBookRepository.save(bookEntity)

        return Book(
            bookId = savedBookEntity.id!!,
            title = savedBookEntity.title,
            content = savedBookEntity.content,
            category = savedBookEntity.category,
            author = savedBookEntity.author,
            createdAt = savedBookEntity.createdAt,
            updatedAt = savedBookEntity.updatedAt
        )
    }

    override fun getBook(bookId: Long): Book {
        val bookEntity = jpaBookRepository.findById(bookId).orElseThrow {
            IllegalArgumentException("Book not found with id: $bookId")
        }

        return Book(
            bookId = bookEntity.id,
            title = bookEntity.title,
            content = bookEntity.content,
            category = bookEntity.category,
            author = bookEntity.author,
            createdAt = bookEntity.createdAt,
            updatedAt = bookEntity.updatedAt
        )
    }

    override fun getBooks(limit: Long, offset: Long): List<Book> {
        return jpaBookRepository.findAll(limit, offset).map {
            Book(
                bookId = it.id,
                title = it.title,
                content = it.content,
                category = it.category,
                author = it.author,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }

    }

    override fun count(limit: Long): Long {
        return jpaBookRepository.count(limit);
    }

    override fun delete(bookId: Long) {

        val book = jpaBookRepository.findById(bookId).orElseThrow {
            IllegalArgumentException("Book not found with id: $bookId")
        }

        jpaBookRepository.delete(book)
    }
}