package tech.jaya.octoevents.service

import tech.jaya.octoevents.exception.BussinessExeption
import tech.jaya.octoevents.model.IssueEvent
import java.lang.IllegalArgumentException

class IssueServiceImpl : IssueService {

    override fun push(issueEvent: IssueEvent): Int {
        validateIssueEvent(issueEvent)
        return 0
    }

    // TODO: Internacionalização
    // TODO: Revisar formatação para expression body
    private fun validateIssueEvent(issueEvent: IssueEvent) = with(issueEvent) {
        val message = "Problem to push event"

        when {
            (id == null) ->
                throw BussinessExeption(message,
                        IllegalArgumentException("The field id is not nullable"))
            (id < 0) ->
                throw BussinessExeption(message,
                        IllegalArgumentException("The field id is ivalid"))
            else -> return@with
        }
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


