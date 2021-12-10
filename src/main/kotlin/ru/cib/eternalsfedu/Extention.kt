package ru.cib.eternalsfedu

import org.apache.commons.io.FileUtils
import ru.cib.eternalsfedu.domain.Event
import ru.cib.eternalsfedu.domain.News
import ru.cib.eternalsfedu.domain.Program
import ru.cib.eternalsfedu.domain.Registration
import ru.cib.eternalsfedu.dto.EventDto
import ru.cib.eternalsfedu.dto.NewsDto
import ru.cib.eternalsfedu.dto.ProgramDto
import ru.cib.eternalsfedu.dto.RegistrationDto
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun NewsDto.toDomain(): News {
    val current = this
    return News().apply {
        title = current.title
        photo = Base64.getDecoder().decode(current.photo)
        text = current.text
    }
}

fun News.toDto(): NewsDto {
    val current = this
    return NewsDto().apply {
        title = current.title
        photo = Base64.getEncoder().encodeToString(current.photo)
        text = current.text
    }
}

fun EventDto.toDomain(): Event {
    val current = this
    return Event().apply {
        date = SimpleDateFormat("dd.MM.yyyy").format(current.date)
        title = current.title
        text = current.text
        photo = Base64.getDecoder().decode(current.photo)
    }
}

fun Event.toDto(): EventDto {
    val current = this
    return EventDto().apply {
        date = SimpleDateFormat("dd.MM.yyyy").parse(current.date)
        title = current.title
        text = current.text
        photo = Base64.getEncoder().encodeToString(current.photo)
    }
}

fun ProgramDto.toDomain(): Program {
    val current = this
    return Program().apply {
        code = current.code
        name = current.name
        score = current.score
    }
}

fun Program.toDto(): ProgramDto {
    val current = this
    return ProgramDto().apply {
        code = current.code
        name = current.name
        score = current.score
    }
}

fun RegistrationDto.toDomain(): Registration {
    val current = this
    return Registration().apply {
        username = current.username
        fio = current.fio
        email = current.email
        phone = current.phone
        photo = Base64.getDecoder().decode(current.photo)
    }
}

fun Registration.toDto(): RegistrationDto {
    val current = this
    return RegistrationDto().apply {
        username = current.username
        fio = current.fio
        email = current.email
        phone = current.phone
        photo = Base64.getEncoder().encodeToString(current.photo)
    }
}