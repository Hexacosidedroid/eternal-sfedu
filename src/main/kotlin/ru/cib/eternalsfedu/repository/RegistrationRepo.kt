package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.Registration

interface RegistrationRepo : PagingAndSortingRepository<Registration, Long> {
}