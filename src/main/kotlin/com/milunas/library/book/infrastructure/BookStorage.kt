package com.milunas.library.book.infrastructure

import javax.persistence.*

@Entity
@Table(name = "books")
data class BookStorage(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int,
                       val isbn: String,
                       val bookStatus: String,
                       val bookRestriction: String)
