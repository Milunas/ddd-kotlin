package com.milunas.library.web.command

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.patron.model.Patron
import com.milunas.library.book.infrastructure.BookRepository
import com.milunas.library.patron.infrastructure.PatronRepository
import com.milunas.library.web.dto.BookID
import com.milunas.library.web.dto.PatronID

class CheckoutBook(private val patrons: PatronRepository,
                   private val books: BookRepository)
{
    // TODO DRY
    fun checkoutBook(patronId: PatronID, bookId: BookID): Result
    {
        val storedPatron = patrons.findById(patronId)
        val storedBook = books.findById(bookId)
        return if (storedPatron != null && storedBook != null)
        {
            val patron = Patron.fromStorage(storedPatron)
            val book = Book.fromStorage(storedBook)
            return if (patron.checkoutBook(book) == Result.SUCCESS)
            {
                val updatedPatron = Patron.toStorage(patron, storedPatron.id)
                val updatedBook = Book.toStorage(book, storedBook.id)
                patrons.save(updatedPatron)
                books.save(updatedBook)
                Result.SUCCESS
            }
            else Result.FAILURE
        }
        else Result.FAILURE
    }
}
