package com.example.bookstore.book.adapter.`in`.web

import com.example.bookstore.book.adapter.`in`.web.request.SaveBookRequest
import com.example.bookstore.book.adapter.`in`.web.request.UpdateBookRequest
import com.example.bookstore.book.adapter.`in`.web.response.BookPageResponse
import com.example.bookstore.book.adapter.`in`.web.response.MetaResponse
import com.example.bookstore.book.adapter.`in`.web.response.ReadBookResponse
import com.example.bookstore.book.adapter.`in`.web.response.SaveBookResponse
import com.example.bookstore.book.adapter.`in`.web.response.UpdateBookResponse
import com.example.bookstore.book.adapter.`in`.web.support.ApiResponse
import com.example.bookstore.book.application.port.`in`.DeleteBookUseCase
import com.example.bookstore.book.application.port.`in`.ReadAllBookUseCase
import com.example.bookstore.book.application.port.`in`.ReadBookUseCase
import com.example.bookstore.book.application.port.`in`.SaveBookCommand
import com.example.bookstore.book.application.port.`in`.SaveBookUseCase
import com.example.bookstore.book.application.port.`in`.UpdateBookCommand
import com.example.bookstore.book.application.port.`in`.UpdateBookUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
@RequestMapping("/api/v1/books")
class BookController(
    private val saveBookUseCase: SaveBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val readAllBookUseCase: ReadAllBookUseCase,
    private val readBookUseCase: ReadBookUseCase
) {

    @GetMapping
    fun getBooks(
        @RequestParam("page") page: Long,
        @RequestParam("pageSize") pageSize: Long
    ): ResponseEntity<ApiResponse<BookPageResponse>> {
        val books = readAllBookUseCase.execute(page, pageSize)

        val readBookResponse = books.book.map { book ->
            ReadBookResponse(
                id = book.bookId!!,
                title = book.title,
                content = book.content,
                category = book.category,
                author = book.author,
                createdAt = book.createdAt,
                updatedAt = book.updatedAt
            )
        }

        return ResponseEntity.ok(
            ApiResponse.success(
                data = BookPageResponse(
                    books = readBookResponse,
                    meta = MetaResponse(
                        bookCount = books.meta.bookCount
                    )
                )
            )
        )
    }

    @GetMapping("/{bookId}")
    fun getBook(@PathVariable bookId: Long): ResponseEntity<ApiResponse<ReadBookResponse>> {
        val book = readBookUseCase.execute(bookId)

        val readBookResponse = ReadBookResponse(
            id = book.bookId!!,
            title = book.title,
            content = book.content,
            category = book.category,
            author = book.author,
            createdAt = book.createdAt,
            updatedAt = book.updatedAt
        )

        return ResponseEntity.ok(
            ApiResponse.success(
                data = readBookResponse
            )
        )
    }


    @PostMapping
    fun create(@RequestBody request: SaveBookRequest): ResponseEntity<ApiResponse<SaveBookResponse>> {
        val command = SaveBookCommand(
            title = request.title,
            content = request.content,
            category = request.category,
            author = request.author
        )

        val book = saveBookUseCase.execute(command)

        return ResponseEntity.created(
            URI.create("/api/v1/books/${book.bookId}")
        ).body(
            ApiResponse.success(
                data = SaveBookResponse(
                    id = book.bookId!!,
                    title = book.title,
                    content = book.content,
                    category = book.category,
                    author = book.author,
                    createdAt = book.createdAt,
                    updatedAt = book.updatedAt
                )
            )
        )
    }

    @PutMapping("/{bookId}")
    fun update(@PathVariable bookId: Long, @RequestBody request: UpdateBookRequest): ResponseEntity<ApiResponse<UpdateBookResponse>> {
        val command = UpdateBookCommand(
            title = request.title,
            content = request.content,
            category = request.category,
            author = request.author
        )

        val book = updateBookUseCase.execute(bookId, command)

        return ResponseEntity.ok(
            ApiResponse.success(
                data = UpdateBookResponse(
                    id = book.bookId!!,
                    title = book.title,
                    content = book.content,
                    category = book.category,
                    author = book.author,
                    createdAt = book.createdAt,
                    updatedAt = book.updatedAt
                )
            )
        )
    }

    @DeleteMapping("/{bookId}")
    fun delete(@PathVariable bookId: Long): ResponseEntity<ApiResponse<Void>> {
        deleteBookUseCase.execute(bookId)
        return ResponseEntity.noContent().build()
    }

}