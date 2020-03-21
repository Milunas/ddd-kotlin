package com.milunas.library.application.domain

import com.milunas.commons.Result
import com.milunas.library.book.model.Book
import com.milunas.library.patron.model.Patron
import spock.lang.Specification

class PatronTest extends Specification
{
    def randomISBN = "1234"
    def available = Book.BookStatus.AVAILABLE
    def notRestricted = Book.BookRestriction.NOT_RESTRICTED
    def book = new Book(randomISBN, available, notRestricted)
    def patron = new Patron()

    def "should place book on hold"()
    {
        expect:
            patron.placeOnHold(book) == Result.SUCCESS
    }

    def "should not place on hold 6th book"()
    {
        given:
            5.times {
                def book = new Book(UUID.randomUUID().toString(), available, notRestricted)
                assert patron.placeOnHold(book) == Result.SUCCESS
            }
        expect:
            patron.placeOnHold(book) == Result.FAILURE
    }

    def "should not place on hold not available book"()
    {
        given:
            def book = new Book(randomISBN, Book.BookStatus.ON_HOLD, notRestricted)
        expect:
            patron.placeOnHold(book) == Result.FAILURE
    }

    def "should not place on hold if patron has 2 overdue"()
    {
        when:
            2.times { patron.overdueCheckoutRegistered() }
        then:
            patron.placeOnHold(book) == Result.FAILURE
    }

    def "should not place on hold restricted book"()
    {
        given:
            def book = new Book(randomISBN, available, Book.BookRestriction.RESTRICTED)
        expect:
            patron.placeOnHold(book) == Result.FAILURE
    }

    def "should cancel hold if patron has a book"() {
        given:
            patron.placeOnHold(book)
        expect:
            patron.cancelHold(book) == Result.SUCCESS
    }

    def "should not cancel hold on book if hold not exist"() {
        expect:
            patron.cancelHold(book) == Result.FAILURE
    }

    def "should not cancel other patron hold"() {
        expect:
            patron.cancelHold(book) == Result.FAILURE
    }

    def "should not cancel same hold twice"() {
        given:
            patron.placeOnHold(book)
        when:
            assert patron.cancelHold(book) == Result.SUCCESS
        then:
            patron.cancelHold(book) == Result.FAILURE
    }

    def "should checkout held book"() {
        given:
            patron.placeOnHold(book)
        expect:
            patron.checkoutBook(book)
    }

    def "should not checkout book hold by other patron"() {
        given:
            def book = new Book(randomISBN, Book.BookStatus.ON_HOLD, notRestricted)
        and:
            def otherPatron = new Patron()
        when:
            otherPatron.placeOnHold(book)
        then:
            patron.checkoutBook(book) == Result.FAILURE
    }

    def "should not checkout book after hold cancellation" () {
        given:
            patron.placeOnHold(book)
        and:
            patron.cancelHold(book)
        expect:
            patron.checkoutBook(book) == Result.FAILURE
    }
}
