package cn.numeron.gson.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime

class LocalDateTimeTypeAdapter(private val converter: Converter = Converter) : TypeAdapter<LocalDateTime>() {

    override fun write(jsonWriter: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            jsonWriter.nullValue()
        } else {
            val text = converter.toText(value)
            jsonWriter.value(text)
        }
    }

    override fun read(jsonReader: JsonReader): LocalDateTime? {
        val jsonToken = jsonReader.peek()
        if (jsonToken == JsonToken.STRING) {
            val string = jsonReader.nextString()
            return converter.toLocalDateTime(string)
        }
        if (jsonToken == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        throw IllegalArgumentException("Parsing the string type to LocalDateTime is supported only.")
    }

    interface Converter {
        fun toText(localDateTime: LocalDateTime): String
        fun toLocalDateTime(text: String): LocalDateTime

        companion object : Converter {
            override fun toText(localDateTime: LocalDateTime): String = localDateTime.toString()
            override fun toLocalDateTime(text: String): LocalDateTime = LocalDateTime.parse(text)
        }
    }

}