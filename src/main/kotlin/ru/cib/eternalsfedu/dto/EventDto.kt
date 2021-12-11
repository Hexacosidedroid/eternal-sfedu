package ru.cib.eternalsfedu.dto

import java.util.*

data class EventDto(
    var date: Date? = null,
    var url: String? = null,
    var title: String? = null,
    var location: String? = null,
    var description: String? = null,
    var image: String? = null
)