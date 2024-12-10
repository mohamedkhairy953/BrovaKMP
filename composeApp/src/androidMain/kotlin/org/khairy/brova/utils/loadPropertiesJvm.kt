package org.khairy.brova.utils

import java.util.Properties

fun loadPropertiesJvm(fileName: String): Map<String, String> {
    val properties = Properties()
    val inputStream = object {}::class.java.classLoader?.getResourceAsStream(fileName)
        ?: throw IllegalArgumentException("File not found: $fileName")
    inputStream.use {
        properties.load(it)
    }
    return properties.entries.associate { it.key.toString() to it.value.toString() }
}
