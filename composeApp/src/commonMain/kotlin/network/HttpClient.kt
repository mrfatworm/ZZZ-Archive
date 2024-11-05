/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import com.mrfatworm.zzzarchive.ZzzConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createZzzHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
//        install(Logging) {
//            logger = Logger.SIMPLE
//            level = LogLevel.ALL
//        }
        defaultRequest {
            url {
                takeFrom("https://raw.githubusercontent.com")
                path("/${ZzzConfig.API_PATH}/")
            }
            contentType(ContentType.Application.Json)
        }
    }
}

fun createOfficialWebHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url {
                takeFrom("https://sg-public-api-static.hoyoverse.com")
            }
        }
    }
}

fun createPixivHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        defaultRequest {
            url {
                takeFrom("https://www.pixiv.net")
            }
        }
    }
}