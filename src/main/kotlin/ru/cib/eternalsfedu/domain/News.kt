package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "news")
data class News(
    var title: String? = null,
    var text: String? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null,
    var photo: String? = null,
    var heatCounter: Long? = null,
    var heatExpirationTime: Date? = null
) : Domain()