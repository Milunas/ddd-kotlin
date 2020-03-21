package com.milunas.library.daily.infrastructure

import com.milunas.library.daily.application.DailyExpiration
import io.micronaut.scheduling.annotation.Scheduled
import java.time.LocalDate
import javax.inject.Singleton

@Singleton
class DailyScheduler(private val expiration: DailyExpiration)
{
    @Scheduled(fixedDelay = "1d")
    fun execute()
    {
        expiration.registerHoldsToExpire(LocalDate.now())
    }
}
