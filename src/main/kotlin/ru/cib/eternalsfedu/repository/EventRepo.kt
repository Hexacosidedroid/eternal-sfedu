package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.Event

interface EventRepo  : PagingAndSortingRepository<Event, Long> {
}