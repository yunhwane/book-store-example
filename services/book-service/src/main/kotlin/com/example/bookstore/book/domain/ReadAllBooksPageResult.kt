package com.example.bookstore.book.domain

data class ReadAllBooksPageResult (
    val meta: Meta,
    val book: List<Book>
){

}
