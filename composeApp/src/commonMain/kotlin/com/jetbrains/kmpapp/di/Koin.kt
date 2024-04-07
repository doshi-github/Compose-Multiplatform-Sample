package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.*
import com.jetbrains.kmpapp.screens.mobile_team.MobileTeamScreenModel
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MobileTeamApi> { KtorMobileTeamApi(get()) }
    single<MobileTeamStorage> { InMemoryMobileTeamStorage() }
    single {
        MobileTeamRepository(get(), get()).apply {
            initialize()
        }
    }
}

val screenModelsModule = module {
    factoryOf(::MobileTeamScreenModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            screenModelsModule,
        )
    }
}
