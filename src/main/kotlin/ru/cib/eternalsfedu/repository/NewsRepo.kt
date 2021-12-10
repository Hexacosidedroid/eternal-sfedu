package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.News

interface NewsRepo : PagingAndSortingRepository<News, Long> {
}