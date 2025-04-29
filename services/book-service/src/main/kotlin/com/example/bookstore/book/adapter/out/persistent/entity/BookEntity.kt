package com.example.bookstore.book.adapter.out.persistent.entity

import com.example.bookstore.book.domain.Category
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "book")
class BookEntity(

    @Id
    val id: Long? = null,

    val title: String,
    val content: String,

    @Enumerated(EnumType.STRING)
    val category: Category,
    val author: String,

    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
}