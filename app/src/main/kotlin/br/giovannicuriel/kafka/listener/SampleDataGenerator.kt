package br.giovannicuriel.kafka.listener

import org.apache.avro.Schema
import org.apache.avro.util.RandomData
import java.io.File


class SampleDataGenerator {
    companion object {
        fun generateRandomDataFromSchema(schemaFile: String): String {
            val data = readFile(schemaFile)
            val schema: Schema = Schema.Parser().parse(data)

            val it: Iterator<Any> = RandomData(schema, 1).iterator()
            return it.next().toString()
        }

        fun readFile(fileName: String): String = File(fileName).readText(Charsets.UTF_8)
    }
}