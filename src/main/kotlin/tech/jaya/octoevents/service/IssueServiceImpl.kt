package tech.jaya.octoevents.service

import org.jetbrains.exposed.sql.transactions.transaction
import tech.jaya.octoevents.exception.BussinessExeption
import tech.jaya.octoevents.model.IssueEvent
import tech.jaya.octoevents.repository.CrudIssueEventRepository

class IssueServiceImpl(val issueRepository: CrudIssueEventRepository) : IssueService {

    override fun push(issueEvent: IssueEvent): Int {
        transaction {
            issueRepository.createTable()
        }

        transaction {
            issueRepository.save(issueEvent)
        }
        return 0
    }

    override fun getIssueEvents(issueNumber: Long): List<IssueEvent> {
        validateIssueNumber(issueNumber)
        return emptyList()
    }

    private fun validateIssueNumber(issueNumber: Long) {
        val message = "Problem to push event"
        when {
            (issueNumber == null) -> throw BussinessExeption(message,
                    IllegalArgumentException("The field id issueNumber isn't nullable"))
            (issueNumber < 0) -> throw BussinessExeption(message,
                    IllegalArgumentException("The field id is ivalid"))
            else -> return
        }
    }

}


