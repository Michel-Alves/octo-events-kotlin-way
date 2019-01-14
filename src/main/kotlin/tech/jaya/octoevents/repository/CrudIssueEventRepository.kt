package tech.jaya.octoevents.repository

import tech.jaya.octoevents.model.IssueEvent

interface CrudIssueEventRepository : CrudRepository<IssueEvent, Int>