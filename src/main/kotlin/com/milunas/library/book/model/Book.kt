package com.milunas.library.book.model

import com.milunas.library.book.infrastructure.BookStorage

typealias ISBN = String
class Book(private val isbn: ISBN,
           private var bookStatus: BookStatus,
           private var bookRestriction: BookRestriction)
{
    enum class BookRestriction { RESTRICTED, NOT_RESTRICTED }
    enum class BookStatus { AVAILABLE, ON_HOLD, NOT_EXIST }

    fun canBeHold(): Boolean =
            isAvailable() && isNotRestricted()

    fun isAvailable(): Boolean =
            bookStatus == BookStatus.AVAILABLE

    fun cancelHold(): BookStatus {
        bookStatus = BookStatus.AVAILABLE
        return bookStatus
    }

    private fun isNotRestricted(): Boolean =
            bookRestriction == BookRestriction.NOT_RESTRICTED

    companion object BookFactory
    {
        fun fromStorage(storage: BookStorage): Book = TODO()
                //Book(storage.isbn, storage.bookStatus, storage.bookRestriction)

        fun toStorage(book: Book, id: Int): BookStorage = TODO()
    }
}
