package com.milunas.library.hold.model

import com.milunas.library.book.model.Book
import java.time.LocalDate

class BookHold(private val book: Book,
               private val holdDate: LocalDate,
               private var expiration: ExpirationStatus = ExpirationStatus.NOT_EXPIRED)
{
    enum class ExpirationStatus { EXPIRED, NOT_EXPIRED }

    fun registerExpiration(): BookHold
    {
        expiration = ExpirationStatus.EXPIRED
        return this
    }

    fun same(givenBook: Book): Boolean =
            book == givenBook
}
