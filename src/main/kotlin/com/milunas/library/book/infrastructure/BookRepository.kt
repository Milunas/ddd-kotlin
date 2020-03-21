package com.milunas.library.book.infrastructure

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.runtime.ApplicationConfiguration
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
class BookRepository(@param:CurrentSession @field:PersistenceContext
                     private val entityManager: EntityManager,
                     private val applicationConfiguration: ApplicationConfiguration)
{
    fun findById(bookId: Int): BookStorage?
    {
        return entityManager.find(BookStorage::class.java, bookId)
    }

    fun save(updatedBook: BookStorage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
