package tech.jaya.octoevents.application

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpHeader
import org.eclipse.jetty.http.HttpStatus
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import tech.jaya.octoevents.config.octoEventsMudule
import tech.jaya.octoevents.controller.IssuesEventRestContract
import tech.jaya.octoevents.model.IssueEvent


class OctoEventsApplication(val javalinInstance: Javalin) : KoinComponent {

    val issueController by inject<IssuesEventRestContract>()

    fun initResourceControllers() {

        javalinInstance.routes {

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

}


