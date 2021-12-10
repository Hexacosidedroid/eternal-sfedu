package ru.cib.eternalsfedu.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.repository.NewsRepo
import ru.cib.eternalsfedu.toDomain

@RestController
class Controller(
    private val newsRepo: NewsRepo
) {

    @GetMapping("/v1/news/getPaged?page={page}")
    fun getPaged() {
    }

    @GetMapping("/v1/news/getAll")
    fun getAll() = newsRepo.findAll()

    @GetMapping("/v1/news/getAllCount")
    fun getAllCount() = newsRepo.findAll().size

    @PostMapping("/v1/news/add")
    fun add(news: NewsDto) = newsRepo.save(news.toDomain())
}