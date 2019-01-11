package tech.jaya.octoevents.controller

import tech.jaya.octoevents.model.IssueEvent
import tech.jaya.octoevents.service.IssueService

// TODO: Faz sentido declarar com object ?
class IssueControllerImpl(val issueService: IssueService) : IssuesEventRestContract {

    override fun registerNewIssueEvent(event: IssueEvent) = issueService.push(event)

    override fun getEvents(issueNumber: Long): List<IssueEvent> = issueService.getIssueEvents(issueNumber)
}