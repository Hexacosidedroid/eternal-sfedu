package ru.cib.eternalsfedu.dto

import javax.persistence.Lob

data class RegistrationDto(
    var username: String? = null,
    var fio: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var photo: String? = null,
)