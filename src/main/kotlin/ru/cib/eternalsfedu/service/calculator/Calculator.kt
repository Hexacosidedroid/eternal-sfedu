package ru.cib.eternalsfedu.service.calculator

import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.repository.ProgramRepo

@Service
class Calculator(
    private val programRepo: ProgramRepo
) {

    fun calculate(score: Map<String, Long>, code: String, name: String) {
        programRepo.findAllByCodeAndName(code, name).forEach {

        }
    }
}