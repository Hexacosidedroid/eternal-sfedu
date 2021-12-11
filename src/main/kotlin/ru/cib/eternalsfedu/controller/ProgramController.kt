package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.cib.eternalsfedu.dto.ProgramDto
import ru.cib.eternalsfedu.repository.ProgramRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class ProgramController(
    private val programRepo: ProgramRepo
) {

    @PostMapping("/v1/program/getMidRange")
    fun getMidRange(@RequestBody code: String): MutableList<ProgramDto> {
        val programs = programRepo.findAllByCode(code)
        val program = mutableListOf<ProgramDto>()
        programs.forEach {
            program.add(it.toDto())
        }
        return program
    }


    @PostMapping("/v1/program/getPaged")
    fun getPaged(@RequestBody page: Long): MutableList<ProgramDto> {
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
    fun add(@RequestBody news: ProgramDto): String {
        return try {
            programRepo.save(news.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}