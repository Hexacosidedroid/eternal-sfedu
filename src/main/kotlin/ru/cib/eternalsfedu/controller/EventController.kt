package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.dto.EventDto
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.repository.EventRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class EventController(
    private val eventRepo: EventRepo
) {
    @GetMapping("/v1/event/getPaged?page={page}")
    fun getPaged(@PathVariable page: Long): MutableList<EventDto> {
        val firstPageWithTenElements = PageRequest.of(page.toInt(), 10)
        val allProducts = eventRepo.findAll(firstPageWithTenElements)
        val events = mutableListOf<EventDto>()
        allProducts.forEach {
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

    @PostMapping("/v1/event/add")
    fun add(news: EventDto): String {
        return try {
            eventRepo.save(news.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}