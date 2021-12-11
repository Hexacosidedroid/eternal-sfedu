package ru.cib.eternalsfedu.dto

data class EventDto(
    var date: String? = null,
    var url: String? = null,
    var title: String? = null,
    var location: String? = null,
    var description: String? = null,
    var image: String? = null
)