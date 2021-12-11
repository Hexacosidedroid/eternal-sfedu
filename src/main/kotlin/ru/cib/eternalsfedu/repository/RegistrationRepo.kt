package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.Registration

interface RegistrationRepo : PagingAndSortingRepository<Registration, Long> {
    fun findByUsername(username: String): Registration
    fun findAllByUsername(username: String): MutableList<Registration>
    fun findAllByUsernameAndPassword(username: String, password: String): MutableList<Registration>
}