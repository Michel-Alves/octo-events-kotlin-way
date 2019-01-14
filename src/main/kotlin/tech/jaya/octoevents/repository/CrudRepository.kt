package tech.jaya.octoevents.repository

interface CrudRepository<E, K> {

    fun getById(id: K) : E?

    fun save(entity: E): K

}
