package com.milunas.library.application.domain

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.hold.infrastructure.BookHoldRepository
import com.milunas.library.hold.model.BookHold
import org.jetbrains.annotations.NotNull

import java.time.LocalDate

class BookHoldRepositoryInMemory implements BookHoldRepository
{
    def randomISBN = UUID.randomUUID().toString()
    def onHold = Book.BookStatus.ON_HOLD
    def notRestricted = Book.BookRestriction.NOT_RESTRICTED
    def book = new Book(randomISBN, onHold, notRestricted)
    def day = LocalDate.now()
    def notExpired = BookHold.ExpirationStatus.NOT_EXPIRED
    def bookHold = new BookHold(book, day, notExpired)

    @Override
    ArrayList<BookHold> findExpiredHolds(@NotNull LocalDate day) { [bookHold] }

    @Override
    Result save(@NotNull BookHold hold) { Result.SUCCESS }
}
