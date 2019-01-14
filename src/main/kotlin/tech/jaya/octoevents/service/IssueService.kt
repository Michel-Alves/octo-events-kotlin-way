package tech.jaya.octoevents.service

import tech.jaya.octoevents.model.IssueEvent

interface IssueService {

    fun push(issueEvent: IssueEvent): Int?

    fun getIssueEvents (issueNumber: Long): List<IssueEvent>

}
