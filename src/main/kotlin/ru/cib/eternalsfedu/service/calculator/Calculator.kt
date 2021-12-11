package ru.cib.eternalsfedu.service.calculator

import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.repository.ProgramRepo

@Service
class Calculator(
    private val programRepo: ProgramRepo
) {

    fun calculate(score: Map<String, Long>, code: String, name: String): Boolean {
        val find = programRepo.findAllByCode(code)
        var response = false
        try {
            find.forEach {
                val result = it.score?.split(" ")
                response = when {
                    score[result!![0]]!! < result[1].toLong() -> throw Exception()
                    score[result[2]]!! < result[3].toLong() -> throw Exception()
                    score[result[4]]!! < result[5].toLong() -> throw Exception()
                    score[result[6]]!! < result[7].toLong() -> throw Exception()
                    else -> true
                }
            }
        } catch (e: Exception) {
            response = false
        }
        return response
    }
}