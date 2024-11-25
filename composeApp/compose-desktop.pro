

# Kotlin
-keep class kotlinx.coroutines.** { *; }
#-keep class **.model.** {@kotlinx.serialization.SerialName <fields>;}

# Ktor
-keep class io.ktor.serialization.kotlinx.** { *; }

# Coil
-keep class coil3.network.ktor3.internal.KtorNetworkFetcherServiceLoaderTarget { *; }
##### Cannot Load Coil3 Image from Network !!!!!!!!

# SQLite
-keep class org.sqlite.** { *; }
-keep class org.sqlite.database.** { *; }

-dontwarn okhttp3.**
