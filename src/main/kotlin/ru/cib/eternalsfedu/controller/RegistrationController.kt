package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.cib.eternalsfedu.dto.RegistrationDto
import ru.cib.eternalsfedu.repository.RegistrationRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class RegistrationController (
        private val registrationRepo: RegistrationRepo
) {
    @PostMapping("/v1/registration/getPaged")
    fun getPaged(@RequestBody page: Long): MutableList<RegistrationDto> {
        val firstPageWithTenElements = PageRequest.of(page.toInt(), 10)
        val allProducts = registrationRepo.findAll(firstPageWithTenElements)
        val registrations = mutableListOf<RegistrationDto>()
        allProducts.forEach {
            registrations.add(it.toDto())
        }
        return registrations
    }

    @GetMapping("/v1/registration/getAll")
    fun getAll(): MutableList<RegistrationDto> {
        val registrations = mutableListOf<RegistrationDto>()
        registrationRepo.findAll().forEach {
            registrations.add(it.toDto())
        }
        return registrations
    }

    @GetMapping("/v1/registration/getAllCount")
    fun getAllCount() = registrationRepo.findAll().toMutableList().size

    @PostMapping("/v1/registration/add")
    fun add(@RequestBody registration: RegistrationDto): String {
        return try {
            registrationRepo.save(registration.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}