package ru.cib.eternalsfedu.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "rank")
data class Rank(
    var fio: String? = null,
    var score: Long? = null
) : Domain()