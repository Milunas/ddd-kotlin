package com.milunas.library.web.query

import com.milunas.library.web.dto.BookDto
import com.milunas.library.web.dto.BookID
import com.milunas.library.web.dto.PatronID

class FindBooks
{
    fun allAvailable(): Set<BookDto>
    {
        TODO()
    }

    fun byBookId(bookId: BookID): BookDto?
    {
        TODO()
    }

    fun checkedByPatron(patronId: PatronID): Set<BookDto>
    {
        TODO()
    }
}
