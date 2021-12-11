package ru.cib.eternalsfedu.service.search

import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.repository.EventRepo

@Service
class Search(
    private val eventRepo: EventRepo
) {

    fun searchEvents(searchText: String) {
        eventRepo.findAllByTextLike(searchText)
    }
}