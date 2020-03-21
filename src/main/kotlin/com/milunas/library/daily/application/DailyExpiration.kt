package com.milunas.library.daily.application

import com.milunas.library.hold.infrastructure.BookHoldRepository
import java.time.LocalDate

class DailyExpiration(private val bookHoldRepository: BookHoldRepository)
{
    fun registerHoldsToExpire(day: LocalDate): Int =
            bookHoldRepository.findExpiredHolds(day)
                    .map { hold -> hold.registerExpiration() }
                    .map { hold -> bookHoldRepository.save(hold) }
                    .count()
}
