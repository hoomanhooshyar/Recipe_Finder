package com.example.recipefinder.util.data_store

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AppUserSerializer:Serializer<AppUser> {
    override val defaultValue: AppUser
        get() = AppUser()

    override suspend fun readFrom(input: InputStream): AppUser {
        return try {
            Json.decodeFromString(
                deserializer = AppUser.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppUser, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = AppUser.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}