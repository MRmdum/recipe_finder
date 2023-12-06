package com.avigationaled

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
            response = client.get(url).body()
            Log.d("KTOR",response as String)
        } catch (e: Exception) {
            throw Error("Error: ${e.localizedMessage}")
        } finally {
            client.close()
        }
        print("a")
        return@runBlocking response
    }
}