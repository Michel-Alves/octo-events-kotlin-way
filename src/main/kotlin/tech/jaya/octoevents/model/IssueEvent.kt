package tech.jaya.octoevents.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import tech.jaya.octoevents.repository.dao.IssueEventDAO

class IssueEvent(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<IssueEvent>(IssueEventDAO)

    var action by IssueEventDAO.action
    
}
