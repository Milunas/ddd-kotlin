package com.milunas.library.patron.infrastructure

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.runtime.ApplicationConfiguration
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
class PatronRepository(@param:CurrentSession @field:PersistenceContext
                       private val entityManager: EntityManager,
                       private val applicationConfiguration: ApplicationConfiguration)
{
    fun findById(patronId: Int): PatronStorage?
    {
        return entityManager.find(PatronStorage::class.java, patronId)
    }

    fun save(updatedPatron: PatronStorage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
