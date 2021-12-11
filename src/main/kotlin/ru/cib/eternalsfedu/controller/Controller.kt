package ru.cib.eternalsfedu.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.domain.Program
import ru.cib.eternalsfedu.dto.*
import ru.cib.eternalsfedu.repository.AchivmentRepo
import ru.cib.eternalsfedu.repository.ProgramRepo
import ru.cib.eternalsfedu.repository.RankRepo
import ru.cib.eternalsfedu.service.calculator.Calculator
import ru.cib.eternalsfedu.toDto

@RestController
class Controller(
    private val calculator: Calculator,
    private val rankRepo: RankRepo,
    private val achivmentRepo: AchivmentRepo,
    private val programRepo: ProgramRepo
) {

    @PostMapping("/v1/calculator")
    fun calculate(@RequestBody calculate: CalculateDto) =
        calculator.calculateBoolean(calculate.score, calculate.code!!, calculate.name!!)

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

    @PostMapping("/v1/filter/getRequest")
    fun filter(@RequestBody filterDto: FilterDto): MutableList<FilterResponse> {
        val programs = mutableListOf<Program>()
        filterDto.programs.forEach {
            when (it) {
                "matem" -> {
                    programs.addAll(programRepo.findAllByCode("02.03.03"))
                    programs.addAll(programRepo.findAllByCode("10.05.02"))
                    programs.addAll(programRepo.findAllByCode("10.05.03"))
                    programs.addAll(programRepo.findAllByCode("10.03.01"))
                }
                "infor" -> {
                    programs.addAll(programRepo.findAllByCode("09.03.01"))
                    programs.addAll(programRepo.findAllByCode("09.03.02"))
                    programs.addAll(programRepo.findAllByCode("09.03.03"))
                    programs.addAll(programRepo.findAllByCode("09.03.04"))
                }
                "other" -> {
                    programs.addAll(programRepo.findAllByCode("17.03.01"))
                    programs.addAll(programRepo.findAllByCode("27.03.03"))
                    programs.addAll(programRepo.findAllByCode("09.05.01"))
                    programs.addAll(programRepo.findAllByCode("10.05.05"))
                }
            }
        }
        val result = mutableListOf<FilterResponse>()
        programs.forEach {
            val percentFake = (filterDto.score!! * 100) / it.midRange!!.toLong()
            result.add(FilterResponse().apply {
                programDto = it.toDto()
                percent = if (percentFake > 100) percentFake - (percentFake % 100) else percentFake
            })
        }
        return result
    }
}