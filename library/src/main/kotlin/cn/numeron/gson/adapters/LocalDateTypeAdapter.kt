package cn.numeron.gson.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.LocalDateTime

class LocalDateTypeAdapter(private val converter: Converter = Converter) : TypeAdapter<LocalDate>() {

    override fun write(jsonWriter: JsonWriter, value: LocalDate?) {
        if (value == null) {
            jsonWriter.nullValue()
        } else {
            val text = converter.toText(value)
            jsonWriter.value(text)
        }
    }

    override fun read(jsonReader: JsonReader): LocalDate? {
        val jsonToken = jsonReader.peek()
        if (jsonToken == JsonToken.STRING) {
            val string = jsonReader.nextString()
            return converter.toLocalDate(string)
        }
        if (jsonToken == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        throw IllegalArgumentException("Parsing the string type to LocalDate is supported only.")
    }

    interface Converter {
        fun toText(localDate: LocalDate): String
        fun toLocalDate(text: String): LocalDate

        companion object : Converter {
            override fun toText(localDate: LocalDate): String = localDate.toString()
            override fun toLocalDate(text: String): LocalDate = LocalDate.parse(text)
        }
    }

}