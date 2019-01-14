package tech.jaya.octoevents.repository.dao

import org.jetbrains.exposed.dao.IntIdTable

object IssueEventDAO : IntIdTable() {
    val action = varchar("action", 30)
}
