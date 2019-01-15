package tech.jaya.octoevents.repository

interface CrudRepository<E, K> {

    fun createTable()

    fun getById(id: K) : E?

    fun save(entity: E): K

}
