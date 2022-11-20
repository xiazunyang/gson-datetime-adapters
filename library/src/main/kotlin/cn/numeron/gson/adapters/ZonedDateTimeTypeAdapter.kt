package cn.numeron.gson.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.ZonedDateTime

class ZonedDateTimeTypeAdapter(private val converter: Converter = Converter) : TypeAdapter<ZonedDateTime>() {

    override fun write(jsonWriter: JsonWriter, value: ZonedDateTime?) {
        if (value == null) {
            jsonWriter.nullValue()
        } else {
            val text = converter.toText(value)
            jsonWriter.value(text)
        }
    }

    override fun read(jsonReader: JsonReader): ZonedDateTime? {
        val jsonToken = jsonReader.peek()
        if (jsonToken == JsonToken.STRING) {
            val string = jsonReader.nextString()
            return converter.toZonedDateTime(string)
        }
        if (jsonToken == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        throw IllegalArgumentException("Parsing the string type to ZonedDateTime is supported only.")
    }

    interface Converter {
        fun toText(zonedDateTime: ZonedDateTime): String
        fun toZonedDateTime(text: String): ZonedDateTime

        companion object : Converter {
            override fun toText(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()
            override fun toZonedDateTime(text: String): ZonedDateTime = ZonedDateTime.parse(text)
        }
    }

}