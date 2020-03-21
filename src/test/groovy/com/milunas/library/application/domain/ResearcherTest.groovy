package com.milunas.library.application.domain

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.patron.model.Researcher
import spock.lang.Specification

class ResearcherTest extends Specification
{
    def randomISBN = "1234"
    def available = Book.BookStatus.AVAILABLE
    def restricted = Book.BookRestriction.RESTRICTED
    def book = new Book(randomISBN, available, restricted)
    def researcher = new Researcher()

    def "should place on hold restricted book"()
    {
        expect:
            researcher.placeOnHold(book) == Result.SUCCESS
    }
}
