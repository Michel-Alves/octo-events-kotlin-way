package tech.jaya.octoevents

import io.javalin.Javalin
import org.koin.standalone.StandAloneContext
import tech.jaya.octoevents.application.OctoEventsApplication
import tech.jaya.octoevents.config.octoEventsMudule

val DEFAULT_PORT = 8080

// TODO: Tem alguma lib nativa para CLI ?
fun main(vararg args: String) {

    var port: Int
    // TODO: Loggar
    // TODO: Validar range de porta
    // TODO: Com certeza tem um jeito mais simples de fazer com Kotlin
    if(args.isNotEmpty()) {
        // try expressions
        try {
            port = args.get(0)?.toInt()
        } catch (e: NumberFormatException) {
            println("Port as ${args.get(0)} not allowed")
        } finally {
            port = DEFAULT_PORT
        }
    } else {
        port = DEFAULT_PORT
    }

    StandAloneContext.startKoin(listOf(octoEventsMudule))

    val javalinInstance =
            Javalin.create()
                    .start(port)

    val app = OctoEventsApplication(javalinInstance)

    app.initResourceControllers()

}
