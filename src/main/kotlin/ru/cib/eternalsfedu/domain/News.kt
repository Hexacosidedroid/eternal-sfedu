package ru.cib.eternalsfedu.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table("news")
data class News(
    var id: Long? = null,
    var title: Long? = null,
    var
)