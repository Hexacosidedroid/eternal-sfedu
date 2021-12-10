package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "events")
data class Event(
    var date: String? = null,
    var url: String? = null,
    var title: String? = null,
    var text: String? = null,
    var location: String? = null,
    @field:Lob
    var photo: ByteArray? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null,
    var heatCounter: Long? = null,
    var heatExpirationTime: Date? = null,
) : Domain()