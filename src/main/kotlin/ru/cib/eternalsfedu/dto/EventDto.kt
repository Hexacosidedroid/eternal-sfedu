package ru.cib.eternalsfedu.dto

import java.util.*

data class EventDto(
    var date: Date? = null,
    var url: String? = null,
    var title: String? = null,
    var text: String? = null,
    var photo: String? = null
)