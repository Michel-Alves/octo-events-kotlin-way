package tech.jaya.octoevents.config

import org.jetbrains.exposed.sql.Table


// Tables Declarations
object IssueEventTable : Table() {
    var id = integer("id").autoIncrement().primaryKey()
    var action = varchar("action", 30)
}