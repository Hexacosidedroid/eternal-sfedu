package ru.cib.eternalsfedu.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.dto.AchivmentDto
import ru.cib.eternalsfedu.dto.CalculateDto
import ru.cib.eternalsfedu.dto.RankDto
import ru.cib.eternalsfedu.repository.AchivmentRepo
import ru.cib.eternalsfedu.repository.RankRepo
import ru.cib.eternalsfedu.service.calculator.Calculator
import ru.cib.eternalsfedu.toDto

@RestController
class Controller(
    private val calculator: Calculator,
    private val rankRepo: RankRepo,
    private val achivmentRepo: AchivmentRepo
) {

    @PostMapping("/v1/calculator")
    fun calculate(@RequestBody calculate: CalculateDto) =
        calculator.calculate(calculate.score, calculate.code!!, calculate.name!!)

    @GetMapping("/v1/ranklist/getAll")
    fun getAllRanks(): MutableList<RankDto> {
        val ranks = mutableListOf<RankDto>()
        rankRepo.findAll().forEach {
            ranks.add(it.toDto())
        }
        return ranks
    }

    @GetMapping("/v1/achivments/getAll")
    fun getAllAchivments(): MutableList<AchivmentDto> {
        val achivments = mutableListOf<AchivmentDto>()
        achivmentRepo.findAll().forEach {
            achivments.add(it.toDto())
        }
        return achivments
    }
}