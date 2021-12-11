package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "program")
data class Program(
    var code: String? = null,
    @Column(length = 2500)
    var name: String? = null,
    var score: String? = null,
    var midRange: String? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null,
) : Domain()