package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "registration")
data class Registration(
    var username: String? = null,
    var fio: String? = null,
    var email: String? = null,
    var phone: String? = null,
    @field:Lob
    var photo: ByteArray? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null,
) : Domain()