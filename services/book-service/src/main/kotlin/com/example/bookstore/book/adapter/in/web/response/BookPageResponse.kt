package com.example.bookstore.book.adapter.`in`.web.response


data class BookPageResponse(
    val meta: MetaResponse,
    val books: List<ReadBookResponse>
)

data class MetaResponse(
    val bookCount: Long,
)