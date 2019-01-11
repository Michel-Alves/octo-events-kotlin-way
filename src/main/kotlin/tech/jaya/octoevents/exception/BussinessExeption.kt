package tech.jaya.octoevents.exception

// TODO: Rever a regra de formatação
class BussinessExeption(
        override val message: String,
        override val cause: Throwable): Throwable() { }