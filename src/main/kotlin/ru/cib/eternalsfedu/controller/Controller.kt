package ru.cib.eternalsfedu.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.domain.Rank
import ru.cib.eternalsfedu.dto.CalculateDto
import ru.cib.eternalsfedu.dto.RankDto
import ru.cib.eternalsfedu.repository.RankRepo
import ru.cib.eternalsfedu.service.calculator.Calculator
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto

@RestController
class Controller(
    private val calculator: Calculator,
    private val rankRepo: RankRepo
) {

    @PostMapping("/v1/calculator")
    fun calculate(@RequestBody calculate: CalculateDto) =
        calculator.calculate(calculate.score, calculate.code!!, calculate.name!!)

    @GetMapping("/v1/ranklist/getAll")
    fun getAll(): MutableList<RankDto> {
        val ranks = mutableListOf<RankDto>()
        rankRepo.findAll().forEach {
            ranks.add(it.toDto())
        }
        return ranks
    }
}