package com.milunas.subscription.domain

import com.milunas.commons.Result
import java.time.Instant

class Subscription(private var status: Status,
                   private val pauses: Pauses) {

    fun activate(): Result {
        status = Status.Activated
        return Result.SUCCESS
    }

    fun deactivate(): Result {
        status = Status.Deactivated
        return Result.SUCCESS
    }

    fun pause(): Result =
            pause(Instant.now())

    fun pause(whenPause: Instant): Result =
            if (isActive() && pauses.canPauseAt(whenPause)) {
                pauses.markNewPauseAt(whenPause)
                status = Status.Paused
                Result.SUCCESS
            } else {
                Result.FAILURE
            }

    private fun isActive(): Boolean =
        status == Status.Activated

    fun resume(): Result =
            if (isPaused()) {
                status = Status.Activated
                Result.SUCCESS
            } else
                Result.FAILURE

    private fun isPaused(): Boolean =
            status == Status.Paused
}
