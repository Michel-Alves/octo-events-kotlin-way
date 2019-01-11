package tech.jaya.octoevents

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpHeader
import org.eclipse.jetty.http.HttpStatus
import tech.jaya.octoevents.controller.IssueControllerImpl
import tech.jaya.octoevents.controller.IssuesEventRestContract
import tech.jaya.octoevents.model.IssueEvent
import tech.jaya.octoevents.service.IssueServiceImpl

val issueController: IssuesEventRestContract = IssueControllerImpl(IssueServiceImpl())

fun main() {
    val app = Javalin.create().start(8080)

    app.routes {

        path("/issues") {

            post {

                val issueEvent = it.validatedBody<IssueEvent>()
                        .getOrThrow()
                val eventId = issueController
                        .registerNewIssueEvent(issueEvent)

                // TODO: Qual a forma kotlin de obter enums http sem depender da lib do jetty?
                it.status(HttpStatus.CREATED_201)
                    .header(HttpHeader.LOCATION.name,
                            "/issues/$eventId/events")
            }

            path("/:issuenumber/events") {

                get {
                    val issueNumber = it.validatedPathParam("issuenumber")
                            .notNullOrEmpty()
                            .asLong()
                            .getOrThrow()

                    it.json(issueController
                            .getEvents(issueNumber))
                            .status(HttpStatus.OK_200)                          
                }

            }

        }
    }
}
