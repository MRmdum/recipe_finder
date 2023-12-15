package com.avigationaled

import android.util.Log
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.*

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class HttpKtorClient {
    fun http_get(url : String): Meal? = runBlocking{

        val client = HttpClient(CIO)
        var response = ""
        try {
            response = client.get(url).body()
            Log.d("KTOR",response as String)
        } catch (e: Exception) {
            throw Error("Error: ${e.localizedMessage}")
        } finally {
            client.close()
        }
        // Parse JSON into the Person data class
        val gson = Gson()
        var repas : Meal? = null
        Log.d("JSON ERREUR",response)
        try {
            repas = Json.decodeFromString<Meal>(response,)

            //repas = gson.fromJson(response, Meal::class.java)
        }
        catch(e: Exception){
            Log.d("JSON ERREUR",e.toString())
        }

        return@runBlocking repas
    }
}