package com.milunas.library.patron.model

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.hold.model.BookHold
import java.time.LocalDate

class Researcher(overdueCheckoutsAmount: Int = 0,
                 heldBooks: MutableSet<BookHold> = mutableSetOf()): Patron(overdueCheckoutsAmount, heldBooks)
{
    override fun placeOnHold(book: Book): Result =
            if (book.isAvailable() && heldBooks.size < 5 && overdueCheckoutsAmount < 2)
            {
                heldBooks.add(BookHold(book, holdDate = LocalDate.now()))
                Result.SUCCESS
            }
            else Result.FAILURE
}
