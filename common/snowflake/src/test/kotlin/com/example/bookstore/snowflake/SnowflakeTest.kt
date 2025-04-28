package com.example.bookstore.snowflake

import Snowflake
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SnowflakeTest {

    @Test
    fun `생성된 ID는 0보다 커야 한다`() {
        val snowflake = Snowflake()
        val id = snowflake.nextId()

        assertTrue(id > 0, "Generated ID should be greater than 0")
    }

    @Test
    fun `여러 개의 ID는 모두 서로 달라야 한다`() {
        val snowflake = Snowflake()

        val ids = mutableSetOf<Long>()
        repeat(1000) {
            val id = snowflake.nextId()
            assertTrue(ids.add(id), "Duplicate ID generated: $id")
        }
    }

    @Test
    fun `밀리초 단위로 생성해도 ID는 유일해야 한다`() {
        val snowflake = Snowflake()

        val firstId = snowflake.nextId()
        val secondId = snowflake.nextId()

        assertTrue(firstId != secondId, "Two IDs should not be equal")
        assertTrue(firstId < secondId, "IDs should be time ordered")
    }
}