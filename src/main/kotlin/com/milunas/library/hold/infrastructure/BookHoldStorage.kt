package com.milunas.library.hold.infrastructure

import com.milunas.library.book.infrastructure.BookStorage
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "holds")
data class BookHoldStorage(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)val id: Int,
                           val book: BookStorage,
                           val holdDate: LocalDate,
                           val expirationStatus: String)
