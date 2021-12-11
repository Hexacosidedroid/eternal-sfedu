package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.cib.eternalsfedu.dto.EventDto
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.repository.EventRepo
import ru.cib.eternalsfedu.service.search.Search
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class EventController(
    private val eventRepo: EventRepo
) {
    @PostMapping("/v1/event/getPaged")
    fun getPaged(@RequestBody page: Long): MutableList<EventDto> {
        val firstPageWithTenElements = PageRequest.of(page.toInt(), 5)
        val allProducts = eventRepo.findAll(firstPageWithTenElements)
        val events = mutableListOf<EventDto>()
        allProducts.forEach {
            events.add(it.toDto())
        }
        return events
    }

    @PostMapping("/v1/event/getByTitle")
    fun getByTitle(@RequestBody title: String): MutableList<EventDto> {
        val events = mutableListOf<EventDto>()
        eventRepo.findAllByTitle(title).forEach {
            events.add(it.toDto())
        }
        return events
    }

    @GetMapping("/v1/event/getAll")
    fun getAll(): MutableList<EventDto> {
        val events = mutableListOf<EventDto>()
        eventRepo.findAll().forEach {
            events.add(it.toDto())
        }
        return events
    }

    @GetMapping("/v1/event/getAllCount")
    fun getAllCount() = eventRepo.findAll().toMutableList().size

    @PostMapping("/v1/event/search")
    fun search(@RequestBody searchText: String): MutableList<EventDto> {
        val events = mutableListOf<EventDto>()
        eventRepo.findAllByTextLike(searchText).forEach {
            events.add(it.toDto())
        }
        return events
    }

    @PostMapping("/v1/event/add")
    fun add(@RequestBody event: EventDto): String {
        return try {
            eventRepo.save(event.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}