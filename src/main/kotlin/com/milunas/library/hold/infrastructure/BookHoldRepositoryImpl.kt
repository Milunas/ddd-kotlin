package com.milunas.library.hold.infrastructure

import com.milunas.commons.Result
import com.milunas.library.hold.model.BookHold
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.runtime.ApplicationConfiguration
import java.time.LocalDate
import java.util.ArrayList
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
class BookHoldRepositoryImpl(@param:CurrentSession @field:PersistenceContext
                             private val entityManager: EntityManager,
                             private val applicationConfiguration: ApplicationConfiguration): BookHoldRepository
{
    override fun findExpiredHolds(day: LocalDate): ArrayList<BookHold>
    {
        TODO()
    }

    override fun save(hold: BookHold): Result
    {
        TODO()
    }
}
