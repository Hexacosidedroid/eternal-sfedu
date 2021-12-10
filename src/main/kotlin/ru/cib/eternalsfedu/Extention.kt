package ru.cib.eternalsfedu

import ru.cib.eternalsfedu.domain.News
import ru.cib.eternalsfedu.dto.NewsDto

fun NewsDto.toDomain(): News {
    val current = this
    return News().apply {
        title = current.title
        photo = current.photo
        text = current.text
    }
}

fun News.toDto(): NewsDto {
    val current = this
    return NewsDto().apply {
        title = current.title
        photo = current.photo
        text = current.text
    }
}