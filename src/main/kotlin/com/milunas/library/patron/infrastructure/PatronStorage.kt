package com.milunas.library.patron.infrastructure

import com.milunas.library.book.infrastructure.BookStorage
import com.milunas.library.hold.infrastructure.BookHoldStorage
import javax.persistence.*

@Entity
@Table(name = "patrons")
data class PatronStorage(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
                         val overdueCheckoutsAmount: Int,
                         val heldBooks: Set<BookHoldStorage>,
                         val checkoutBooks: Set<BookStorage>)
