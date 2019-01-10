package tech.jaya.octoevents

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpHeader
import org.eclipse.jetty.http.HttpStatus

fun main() {
    val app = Javalin.create().start(8080)

    // POST /issues and GET /issues/{id}/events
    app.routes {
        path("/issues") {
            post {
                // TODO: Qual a forma kotlin way de obter enums http sem depender da lib do jetty?
                /*
                   Teste:
                   $ curl -X POST -d '{}' 'http://localhost:8080/issues' -v
                 */
                it.status(HttpStatus.CREATED_201)
                    .header(HttpHeader.LOCATION.name,
                            "/issues/1000/events")
            }
            path("/:idissue/events") {
                get {
                    it.json("{\"msg\": \"ok\"}").status(HttpStatus.OK_200)
                }
            }
        }
    }
}
