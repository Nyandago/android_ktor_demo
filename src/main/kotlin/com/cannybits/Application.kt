package com.cannybits

import io.ktor.server.application.*
import com.cannybits.plugins.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    //configureRouting()
//    routing {
//        get("/") {
//            call.respondText("Welcome to Canny ville!!")
//        }
//    }

    install(Routing){
        get("/"){
            call.respondText("welcome to Canny-ville")
        }

        get("/users/{username}"){
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]

            if (username == "Admin"){
                call.response.header(name = "CustomHeader","Admin")
                call.respond(message="Hello Admin", status = HttpStatusCode.OK)
            }

            call.respondText("username fetched is $username with header $header")
        }

        get("/user"){
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]

            call.respondText("My name is $name and I am $age years old")
        }
    }
}
