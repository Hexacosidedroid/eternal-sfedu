package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "achivments")
data class Achivment(
    var name: String? = null,
    var text: String? = null,
    @field:Lob
    var photo: ByteArray? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null
) : Domain()