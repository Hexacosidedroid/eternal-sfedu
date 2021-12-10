package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.Program

interface ProgramRepo : PagingAndSortingRepository<Program, Long> {
    fun findAllByCodeAndName(code: String, name: String): MutableList<Program>
}