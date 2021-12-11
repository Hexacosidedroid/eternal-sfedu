package ru.cib.eternalsfedu.dto

data class RegistrationDto (
    var username: String? = null,
    var fio: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var snils: String? = null,
    var password: String? = null
)