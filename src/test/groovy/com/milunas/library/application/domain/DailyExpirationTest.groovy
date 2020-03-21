package com.milunas.library.application.domain

import com.milunas.library.daily.application.DailyExpiration
import spock.lang.Specification
import java.time.LocalDate

class DailyExpirationTest extends Specification
{
    def bookHoldRepository = new BookHoldRepositoryInMemory()
    def dailyExpiration = new DailyExpiration(bookHoldRepository)

    def "should return amount of expired holds"()
    {
        given:
            def day = LocalDate.now()
        expect:
            dailyExpiration.registerHoldsToExpire(day) == 1
    }
}
