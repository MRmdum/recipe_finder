package com.avigationaled

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.*

import kotlinx.coroutines.runBlocking
class HttpKtorClient {
    fun http_get(url : String): String = runBlocking{

        val client = HttpClient(CIO)
        var response : String
        try {
            response = client.get(url) as String
        } catch (e: Exception) {
            response = "Error: ${e.localizedMessage}"
        } finally {
            client.close()
        }
        return@runBlocking response
    }
}