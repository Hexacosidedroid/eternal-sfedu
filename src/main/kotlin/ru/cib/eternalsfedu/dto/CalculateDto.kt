package ru.cib.eternalsfedu.dto

data class CalculateDto(
    var score: Map<String, Long> = mapOf(),
    var code: String? = null,
    var name: String? = null
)