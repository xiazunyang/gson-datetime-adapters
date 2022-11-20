package cn.numeron.gson.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.Date

class DateTypeAdapter(private val converter: Converter = Converter) : TypeAdapter<Date>() {

    override fun write(jsonWriter: JsonWriter, value: Date?) {
        if (value == null) {
            jsonWriter.nullValue()
        } else {
            val text = converter.toText(value)
            jsonWriter.value(text)
        }
    }

    override fun read(jsonReader: JsonReader): Date? {
        val jsonToken = jsonReader.peek()
        if (jsonToken == JsonToken.STRING) {
            val string = jsonReader.nextString()
            return converter.toDate(string)
        }
        if (jsonToken == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        throw IllegalArgumentException("Parsing the string type to Date is supported only.")
    }

    interface Converter {
        fun toText(date: Date): String
        fun toDate(text: String): Date

        companion object DEFAULT : Converter {
            private val DATE_FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            override fun toText(date: Date): String = DATE_FORMATTER.format(date)
            override fun toDate(text: String): Date = DATE_FORMATTER.parse(text)
        }
    }

}