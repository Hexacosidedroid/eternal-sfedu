package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.dto.ProgramDto
import ru.cib.eternalsfedu.repository.ProgramRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class ProgramController(
    private val programRepo: ProgramRepo
) {

    @GetMapping("/v1/program/getPaged?page={page}")
    fun getPaged(@PathVariable page: Long): MutableList<ProgramDto> {
        val firstPageWithTenElements = PageRequest.of(page.toInt(), 5)
        val allProducts = programRepo.findAll(firstPageWithTenElements)
        val program = mutableListOf<ProgramDto>()
        allProducts.forEach {
            program.add(it.toDto())
        }
        return program
    }

    @GetMapping("/v1/program/getAll")
    fun getAll(): MutableList<ProgramDto> {
        val program = mutableListOf<ProgramDto>()
        programRepo.findAll().forEach {
            program.add(it.toDto())
        }
        return program
    }

    @GetMapping("/v1/program/getAllCount")
    fun getAllCount() = programRepo.findAll().toMutableList().size

    @PostMapping("/v1/program/add")
    fun add(news: ProgramDto): String {
        return try {
            programRepo.save(news.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}