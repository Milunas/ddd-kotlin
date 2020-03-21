package com.milunas.library.hold.infrastructure

import com.milunas.commons.Result
import com.milunas.library.hold.model.BookHold
import java.time.LocalDate
import java.util.ArrayList

interface BookHoldRepository
{
    fun findExpiredHolds(day: LocalDate): ArrayList<BookHold>
    fun save(hold: BookHold): Result
}
