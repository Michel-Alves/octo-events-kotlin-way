package tech.jaya.octoevents.repository

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import tech.jaya.octoevents.config.IssueEventTable
import tech.jaya.octoevents.model.IssueEvent

class CrudIssueEventRepositoryImpl : CrudIssueEventRepository {

    override fun createTable() {
        //Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        Database.connect("jdbc:sqlite:file:test?mode=memory&cache=shared", driver = "org.sqlite.JDBC")

        SchemaUtils.create(IssueEventTable)
    }

    override fun getById(id: Int): IssueEvent? {
        return null
    }

    override fun save(issueEvent: IssueEvent): Int {
        issueEvent.id = transaction {
            IssueEventTable.insert(toRow(issueEvent))[IssueEventTable.id]
        }
        return issueEvent.id!!
    }

    private fun toRow(issueEvent: IssueEvent): IssueEventTable.(UpdateBuilder<*>) -> Unit = {
        val modelId = issueEvent.id
        if(modelId != null) it[id] = modelId

        val modelAction = issueEvent.action
        if(modelAction != null) it[action] = modelAction
    }

}