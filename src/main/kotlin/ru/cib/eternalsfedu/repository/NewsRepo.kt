package ru.cib.eternalsfedu.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.cib.eternalsfedu.domain.News

interface NewsRepo : JpaRepository<News, Long> {
}