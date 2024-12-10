package org.khairy.brova.utils

actual fun loadProperties(fileName: String): Map<String, String> {
    return loadPropertiesJvm(fileName)
}