package com.avigationaled

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.OkHttp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.security.AccessController.getContext
import kotlin.coroutines.CoroutineContext

class HttpKtorClient : CoroutineScope {
    override val coroutineContext: CoroutineContext = Job()

    suspend fun http_get(url : String): ListMeal? {
        val client = HttpClient(CIO)
        var response = ""
        try {
            response = client.get(url).body()
            Log.d("KTOR",response as String)
        } catch (e: Exception) {
            Log.d("HttpError",e.toString())
        } finally {
            client.close()
        }
        // Parse JSON into the Person data class
        var repas : ListMeal? = null
        try {
            repas = Json { ignoreUnknownKeys = true }.decodeFromString(response)
        }
        catch(e: Exception){
            Log.d("JSON ERREUR",e.toString())
        }

        return repas
    }
    suspend fun fromHttpGetWriteDB(url : String, context: Context){
        val mealFetched = withContext(Dispatchers.IO) {
            HttpKtorClient().http_get(url)
        }
        var repo = UserRepository(context)
        if (mealFetched != null) {
            if (mealFetched.meals.isNotEmpty())
                for(meal in mealFetched.meals){
                    try {
                        repo.insertMeal(meal)
                        Log.d("SQLite","Row Added")
                    }catch (e : SQLiteConstraintException){
                        Log.d("SQLiteConstraintException",e.toString())
                    }
                }
        }
    }
}