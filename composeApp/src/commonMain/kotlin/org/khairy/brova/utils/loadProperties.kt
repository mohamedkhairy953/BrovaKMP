package org.khairy.brova.utils


import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.buffer

expect fun loadProperties(fileName: String): Map<String, String>
