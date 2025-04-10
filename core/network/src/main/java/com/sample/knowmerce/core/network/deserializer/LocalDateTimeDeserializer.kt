package com.sample.knowmerce.core.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return runCatching {
            json?.asString?.let {
                val offsetDateTime = OffsetDateTime.parse(it, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                offsetDateTime.toLocalDateTime()
            }
        }.getOrNull()
    }
}