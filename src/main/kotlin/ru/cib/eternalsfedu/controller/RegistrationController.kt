package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.repository.NewsRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class RegistrationController (
        private val registrationRepo: RegistrationRepo
) {
    @GetMapping("/v1/news/getPaged?page={page}")
    fun getPaged(@PathVariable page: Long): MutableList<RegistrationDto> {
        val firstPageWithTenElements = PageRequest.of(page.toInt(), 10)
        val allProducts = registrationRepo.findAll(firstPageWithTenElements)
        val registrations = mutableListOf<RegistrationDto>()
        allProducts.forEach {
            registrations.add(it.toDto())
        }
        return registrations
    }

    @GetMapping("/v1/news/getAll")
    fun getAll(): MutableList<RegistrationDto> {
        val registrations = mutableListOf<RegistrationDto>()
        registrationRepo.findAll().forEach {
            registrations.add(it.toDto())
        }
        return registrations
    }

    @GetMapping("/v1/news/getAllCount")
    fun getAllCount() = registrationRepo.findAll().toMutableList().size

    @PostMapping("/v1/news/add")
    fun add(registration: RegistrationDto): String {
        return try {
            registrationRepo.save(registration.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}