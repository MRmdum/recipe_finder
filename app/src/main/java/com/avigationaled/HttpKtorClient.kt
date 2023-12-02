package com.avigationaled

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.*

import kotlinx.coroutines.runBlocking
class HttpKtorClient {
    fun http_get(url : String): Meal = runBlocking{

        val client = HttpClient(CIO)
        var response : Meal
        try {
            response = client.get(url).body() as Meal
        } catch (e: Exception) {
            throw Error("Error: ${e.localizedMessage}")
        } finally {
            client.close()
        }
        return@runBlocking response
    }
}