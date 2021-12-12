package ru.cib.eternalsfedu

import ru.cib.eternalsfedu.domain.*
import ru.cib.eternalsfedu.dto.*
import java.text.SimpleDateFormat
import java.util.*

fun NewsDto.toDomain(): News {
    val current = this
    return News().apply {
        title = current.title
        photo = Base64.getDecoder().decode(current.image)
        text = current.description
    }
}

fun News.toDto(): NewsDto {
    val current = this
    return NewsDto().apply {
        title = current.title
        image = Base64.getEncoder().encodeToString(current.photo)
        description = current.text
    }
}

fun EventDto.toDomain(): Event {
    val current = this
    return Event().apply {
        date = current.date
        title = current.title
        description = current.description
        image = Base64.getDecoder().decode(current.image)
    }
}

fun Event.toDto(): EventDto {
    val current = this
    return EventDto().apply {
        date = current.date
        title = current.title
        description = current.description
        image = Base64.getEncoder().encodeToString(current.image)
    }
}

fun ProgramDto.toDomain(): Program {
    val current = this
    return Program().apply {
        code = current.code
        name = current.name
        score = current.score
        midRange = current.midRange
    }
}

fun Program.toDto(): ProgramDto {
    val current = this
    return ProgramDto().apply {
        code = current.code
        name = current.name
        score = current.score
        midRange = current.midRange
    }
}

fun RegistrationDto.toDomain(): Registration {
    val current = this
    return Registration().apply {
        username = current.username
        fio = current.fio
        email = current.email
        phone = current.phone
        snils = current.snils
        password = current.password
    }
}

fun Registration.toDto(): RegistrationDto {
    val current = this
    return RegistrationDto().apply {
        username = current.username
        fio = current.fio
        email = current.email
        phone = current.phone
        snils = current.snils
        password = current.password
    }
}

fun RankDto.toDomain(): Rank {
    val current = this
    return Rank().apply {
        snils = current.snils
        score = current.score?.toLong()
    }
}

fun Rank.toDto(): RankDto {
    val current = this
    return RankDto().apply {
        snils = current.snils
        score = current.score.toString()
    }
}

fun AchivmentDto.toDomain(): Achivment {
    val current = this
    return Achivment().apply {
        name = current.name
        text = current.text
        photo = Base64.getDecoder().decode(current.photo)
    }
}

fun Achivment.toDto(): AchivmentDto {
    val current = this
    return AchivmentDto().apply {
        name = current.name
        text = current.text
        photo = Base64.getEncoder().encodeToString(current.photo)
    }
}