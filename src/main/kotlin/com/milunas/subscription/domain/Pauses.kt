package com.milunas.subscription.domain

import java.time.Duration
import java.time.Instant

class Pauses(private var yetAvailablePauses: Int,
             private var lastPause: Instant?) {

    fun canPauseAt(whenPause: Instant): Boolean =
            enoughDaysSinceLastPause(whenPause) && anyPauseAvailable()

    fun markNewPauseAt(whenPause: Instant) {
        yetAvailablePauses -= 1
        lastPause = whenPause
    }

    private fun anyPauseAvailable(): Boolean {
        return yetAvailablePauses > 0
    }

    private fun enoughDaysSinceLastPause(whenPause: Instant): Boolean {
        if (lastPause == null) {
            return true
        }
        return Duration.between(lastPause, whenPause).toDays() >= 10
    }

}
