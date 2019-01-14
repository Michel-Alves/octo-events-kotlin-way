package tech.jaya.octoevents.config

import org.koin.dsl.module.module
import tech.jaya.octoevents.controller.IssueControllerImpl
import tech.jaya.octoevents.controller.IssuesEventRestContract
import tech.jaya.octoevents.service.IssueService
import tech.jaya.octoevents.service.IssueServiceImpl

val octoEventsMudule = module {

    single { IssueServiceImpl() as IssueService }

    single { IssueControllerImpl(get()) as IssuesEventRestContract }

}