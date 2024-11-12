package com.gfiber.useractiontracking.util

import com.fasterxml.jackson.databind.ObjectMapper

private val objectMapper = ObjectMapper()

fun Map<String, Any>?.toSelectionData(): String {
    return this?.let { objectMapper.writeValueAsString(it) }.orEmpty()
}

fun String.toSelectionData(): Map<String, Any> {
    return objectMapper.readValue(this, Map::class.java) as Map<String, Any>
}
