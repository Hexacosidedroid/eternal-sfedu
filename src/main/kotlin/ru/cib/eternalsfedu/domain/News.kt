package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "news")
data class News(
    var date: String? = null,
    var title: String? = null,
    var text: String? = null,
    @field:Lob
    var photo: ByteArray? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null
) : Domain()