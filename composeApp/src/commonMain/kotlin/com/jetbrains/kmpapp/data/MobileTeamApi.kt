package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.utils.io.CancellationException

interface MobileTeamApi {
    suspend fun getData(): MobileTeam
}

class KtorMobileTeamApi(private val client: HttpClient) : MobileTeamApi {
    companion object {
        private const val API_URL =
            "https://huge-perch-53.hasura.app/api/rest/mobile-devs"
        private const val HEADER_CONTENT = "content-type"
        private const val HEADER_CONTENT_VALUE = "application/json"
        private const val HEADER_HASURA_SECRET = "x-hasura-admin-secret"
        private const val HEADER_HASURA_SECRET_VALUE =
            "aDVwD6oFpKss43fmsQZlmT9Rl7Su6S15hP2qs3QYTr3m2s35hMjBv9kKQeWmFFeS"
    }

    override suspend fun getData(): MobileTeam {
        return try {
            client.get(API_URL){
                headers {
                    append(HEADER_CONTENT, HEADER_CONTENT_VALUE)
                    append(HEADER_HASURA_SECRET, HEADER_HASURA_SECRET_VALUE)
                }
            }.body()
        }
        catch (exception: Exception) {
            if (exception is CancellationException)
                throw exception
            exception.printStackTrace()

            MobileTeam(emptyList())
        }
    }
}