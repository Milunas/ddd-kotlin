package com.milunas.library.patron.model

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.hold.model.BookHold
import com.milunas.library.patron.infrastructure.PatronStorage
import java.time.LocalDate

open class Patron(protected var overdueCheckoutsAmount: Int = 0,
                  protected var heldBooks: MutableSet<BookHold> = mutableSetOf(),
                  private var checkoutBooks: MutableSet<Book> = mutableSetOf())
{
    open fun placeOnHold(book: Book): Result =
            if(book.canBeHold() && heldBooks.size < 5 && overdueCheckoutsAmount < 2)
            {
                heldBooks.add(BookHold(book, holdDate = LocalDate.now()))
                Result.SUCCESS
            }
            else Result.FAILURE

    fun overdueCheckoutRegistered(): Int
    {
        overdueCheckoutsAmount+=1
        return overdueCheckoutsAmount
    }

    fun cancelHold(book: Book): Result {
        val bookHold = heldBooks.find { heldBook -> heldBook.same(book) }
        return if(bookHold != null)
        {
            book.cancelHold()
            heldBooks.remove(bookHold)
            Result.SUCCESS
        }
        else Result.FAILURE
    }

    fun checkoutBook(book: Book): Result
    {
        val bookHold = heldBooks.find { heldBook -> heldBook.same(book) }
        return if(bookHold!=null)
        {
            heldBooks.remove(bookHold)
            checkoutBooks.add(book)
            Result.SUCCESS
        }
        else Result.FAILURE
    }

    companion object PatronFactory
    {
        fun fromStorage(storage: PatronStorage): Patron = TODO()
        //Patron(storage.overdueCheckoutsAmount, storage.heldBooks, storage.checkoutBooks)

        fun toStorage(patron: Patron, id: Int): PatronStorage = TODO()
        //PatronStorage(id, patron.overdueCheckoutsAmount, patron.heldBooks, patron.checkoutBooks)
    }
}
