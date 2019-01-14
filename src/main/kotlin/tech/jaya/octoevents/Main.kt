package tech.jaya.octoevents

import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.standalone.StandAloneContext
import tech.jaya.octoevents.application.OctoEventsApplication
import tech.jaya.octoevents.config.octoEventsMudule
import tech.jaya.octoevents.repository.dao.IssueEventDAO

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

    initDatabase()

    app.initResourceControllers()

}

fun initDatabase() {

    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

    transaction {
        // Irá printar SQL para std-out
        addLogger(StdOutSqlLogger)

        // Criando as tabelas se não existirem
        SchemaUtils.create(IssueEventDAO)
    }
}
