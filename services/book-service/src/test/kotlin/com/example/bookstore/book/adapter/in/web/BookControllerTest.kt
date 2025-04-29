package com.example.bookstore.book.adapter.`in`.web

import com.example.bookstore.book.adapter.`in`.web.request.SaveBookRequest
import com.example.bookstore.book.adapter.`in`.web.request.UpdateBookRequest
import com.example.bookstore.book.application.port.`in`.DeleteBookUseCase
import com.example.bookstore.book.application.port.`in`.SaveBookUseCase
import com.example.bookstore.book.application.port.`in`.UpdateBookUseCase
import com.example.bookstore.book.domain.Book
import com.example.bookstore.book.domain.Category
import com.example.bookstore.test.api.RestDocsTest
import com.example.bookstore.test.api.RestDocsUtils.requestPreprocessor
import com.example.bookstore.test.api.RestDocsUtils.responsePreprocessor
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import java.time.LocalDateTime


class BookControllerTest : RestDocsTest() {

    private lateinit var saveBookUseCase: SaveBookUseCase
    private lateinit var updateBookUseCase: UpdateBookUseCase
    private lateinit var deleteBookUseCase: DeleteBookUseCase
    private lateinit var saveBookController: BookController

    @BeforeEach
    fun setUp() {
        saveBookUseCase = mockk(relaxUnitFun = true)
        updateBookUseCase = mockk(relaxUnitFun = true)
        deleteBookUseCase = mockk(relaxUnitFun = true)
        var saveBookController = BookController(saveBookUseCase, updateBookUseCase, deleteBookUseCase)
        mockMvc = mockController(saveBookController)
    }

    @Test
    fun should_create_book_when_request_is_valid_201() {
        val request = SaveBookRequest(
            title = "테스트 책 제목",
            content = "테스트 책 내용",
            category = Category.FANTASY,
            author = "홍길동"
        )

        val result = Book(
            bookId = 1L,
            title = request.title,
            content = request.content,
            category = request.category,
            author = request.author,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        every {
            saveBookUseCase.execute(any())
        } returns result

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/api/v1/books")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .apply(
                document(
                    "create-book",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("책 제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("책 내용"),
                        fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                        fieldWithPath("author").type(JsonFieldType.STRING).description("저자")
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("요청 처리 결과 (SUCCESS 또는 ERROR)"),
                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("책 ID"),
                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("책 제목"),
                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("책 내용"),
                        fieldWithPath("data.category").type(JsonFieldType.STRING).description("카테고리"),
                        fieldWithPath("data.author").type(JsonFieldType.STRING).description("저자"),
                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("생성 일시 (ISO8601 포맷)"),
                        fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("수정 일시 (ISO8601 포맷)"),
                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보 (성공시 null)")
                    )
                )
            )
    }

    @Test
    fun should_update_book_when_request_is_valid_200() {
        // given
        val request = UpdateBookRequest(
            title = "수정된 책 제목",
            content = "수정된 책 내용",
            category = Category.FANTASY,
            author = "이몽룡"
        )

        val result = Book(
            bookId = 1L,
            title = request.title,
            content = request.content,
            category = request.category,
            author = request.author,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        every {
            updateBookUseCase.execute(1L, any())
        } returns result

        // when
        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .put("/api/v1/books/{bookId}", 1L)
            .then()
            .statusCode(HttpStatus.OK.value())
            .apply(
                document(
                    "update-book",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    pathParameters(
                        parameterWithName("bookId").description("수정할 책 ID")
                    ),
                    requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("수정할 책 제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 책 내용"),
                        fieldWithPath("category").type(JsonFieldType.STRING).description("수정할 카테고리"),
                        fieldWithPath("author").type(JsonFieldType.STRING).description("수정할 저자")
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("요청 결과 (SUCCESS 또는 ERROR)"),
                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("책 ID"),
                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("수정된 책 제목"),
                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("수정된 책 내용"),
                        fieldWithPath("data.category").type(JsonFieldType.STRING).description("수정된 카테고리"),
                        fieldWithPath("data.author").type(JsonFieldType.STRING).description("수정된 저자"),
                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("책 최초 생성일 (ISO8601 포맷)"),
                        fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("책 수정일시 (ISO8601 포맷)"),
                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러 정보 (성공시 null)")
                    )
                )
            )
    }

    @Test
    fun should_delete_book_when_request_is_valid_204() {
        // given
        every { deleteBookUseCase.execute(1L) } just Runs

        // when
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .delete("/api/v1/books/{bookId}", 1L)
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .apply(
                document(
                    "delete-book",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    pathParameters(
                        parameterWithName("bookId").description("삭제할 책 ID")
                    )
                )
            )
    }
}