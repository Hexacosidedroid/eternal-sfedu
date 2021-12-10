package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*

data class Program(
    var code: String? = null,
    var name: String? = null,
    var score: String? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null,
) : Domain()