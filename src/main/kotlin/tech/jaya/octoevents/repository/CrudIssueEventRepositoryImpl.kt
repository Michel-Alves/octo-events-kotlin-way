package tech.jaya.octoevents.repository

import tech.jaya.octoevents.model.IssueEvent

class CrudIssueEventRepositoryImpl : CrudIssueEventRepository {
    override fun getById(id: Int): IssueEvent? {
        return IssueEvent.findById(id)
    }

    override fun save(issueEvent: IssueEvent): Int {
        // TODO: Procurar outra forma de inserir sem precisar setar cada propriedade
        return IssueEvent.new {
            action = issueEvent.action
        }.id.value
    }

}