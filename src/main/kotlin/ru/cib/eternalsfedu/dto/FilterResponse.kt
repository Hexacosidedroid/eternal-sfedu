package ru.cib.eternalsfedu.dto

data class FilterResponse(
    var programDto: ProgramDto? = null,
    var percent: Long? = null
)