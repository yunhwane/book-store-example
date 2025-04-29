package com.example.bookstore.book.adapter.out.persistent.repository

import com.example.bookstore.book.adapter.out.persistent.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JpaBookRepository : JpaRepository<BookEntity, Long> {

}