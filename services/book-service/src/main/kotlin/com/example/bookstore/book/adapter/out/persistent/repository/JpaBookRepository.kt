package com.example.bookstore.book.adapter.out.persistent.repository

import com.example.bookstore.book.adapter.out.persistent.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface JpaBookRepository : JpaRepository<BookEntity, Long> {

    @Query(
        value = """
        SELECT id, title, content, category, author, created_at, updated_at
        FROM book
        ORDER BY id DESC
        LIMIT :limit OFFSET :offset
    """,
        nativeQuery = true
    )
    fun findAll(
        @Param("limit") limit: Long,
        @Param("offset") offset: Long
    ): List<BookEntity>

    @Query(
        value = """
        SELECT COUNT(*)
        FROM book
        Limit :limit
    """,
        nativeQuery = true
    )
    fun count(@Param("limit") limit: Long): Long

}