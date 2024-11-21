package org.khairy.brova

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform