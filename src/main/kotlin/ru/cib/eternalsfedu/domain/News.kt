package ru.cib.eternalsfedu.domain

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "news")
data class News(
    var date: String? = null,
    var title: String? = null,
    @Column(length = 2500)
    var description: String? = null,
    @field:Lob
    var image: ByteArray? = null,
    @field:CreationTimestamp
    var creationTime: Date? = null
) : Domain()