package tech.jaya.octoevents.controller

import tech.jaya.octoevents.model.IssueEvent

interface IssuesEventRestContract {

    fun registerNewIssueEvent(event: IssueEvent): Int?

    fun getEvents(issueNumber: Long) : List<IssueEvent>

}
