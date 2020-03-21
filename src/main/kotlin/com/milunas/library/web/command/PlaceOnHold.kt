package com.milunas.library.web.command

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.patron.model.Patron
import com.milunas.library.book.infrastructure.BookRepository
import com.milunas.library.patron.infrastructure.PatronRepository

class PlaceOnHold(private val patrons: PatronRepository,
                  private val books: BookRepository)
{
    // TODO transactional
    // TODO refactor to monad
    fun placeOnHold(patronId: Int, bookId: Int): Result
    {
        // TODO get from database
        val storedPatron = patrons.findById(patronId)
        val storedBook = books.findById(bookId)
        // TODO onEmpty
        // TODO map to tuple
        return if (storedPatron != null && storedBook != null)
        {
            // TODO smart map with map struct?
            val patron = Patron.fromStorage(storedPatron)
            val book = Book.fromStorage(storedBook)
            // TODO result as either
            return if (patron.placeOnHold(book) == Result.SUCCESS)
            {
                // TODO map
                val updatedPatron = Patron.toStorage(patron, storedPatron.id)
                val updatedBook = Book.toStorage(book, storedBook.id)
                // TODO map
                patrons.save(updatedPatron)
                books.save(updatedBook)
                Result.SUCCESS
            }
            else Result.FAILURE
        }
        else Result.FAILURE
    }
}
