package ru.cib.eternalsfedu.controller

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.repository.NewsRepo
import ru.cib.eternalsfedu.toDomain
import ru.cib.eternalsfedu.toDto


@RestController
class NewsController(
    private val newsRepo: NewsRepo
) {
    @PostMapping("/v1/news/getPaged")
    fun getPaged(@RequestBody page: Int): MutableList<NewsDto> {
        val firstPageWithTenElements = PageRequest.of(page, 5)
        val allProducts = newsRepo.findAll(firstPageWithTenElements)
        val news = mutableListOf<NewsDto>()
        allProducts.forEach {
            news.add(it.toDto())
        }
        return news
    }

    @GetMapping("/v1/news/getAll")
    fun getAll(): MutableList<NewsDto> {
        val news = mutableListOf<NewsDto>()
        newsRepo.findAll().forEach {
            news.add(it.toDto())
        }
        return news
    }

    @GetMapping("/v1/news/getAllCount")
    fun getAllCount() = newsRepo.findAll().toMutableList().size

    @PostMapping("/v1/news/add")
    fun add(@RequestBody news: NewsDto): String {
        return try {
            newsRepo.save(news.toDomain())
            "OK"
        } catch (e: Exception) {
            e.message!!
        }
    }
}