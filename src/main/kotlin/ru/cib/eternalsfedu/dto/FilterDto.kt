package ru.cib.eternalsfedu.dto

data class FilterDto(
    var programs: MutableList<String> = mutableListOf(),
    var score: Long? = null
)