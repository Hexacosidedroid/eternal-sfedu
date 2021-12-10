package ru.cib.eternalsfedu

import org.apache.commons.io.FileUtils
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.domain.Event
import ru.cib.eternalsfedu.domain.News
import ru.cib.eternalsfedu.domain.Program
import ru.cib.eternalsfedu.repository.EventRepo
import ru.cib.eternalsfedu.repository.NewsRepo
import ru.cib.eternalsfedu.repository.ProgramRepo
import java.io.File
import java.nio.charset.Charset
import java.util.*

@Service
class Task(
    private val newsRepo: NewsRepo,
    private val eventRepo: EventRepo,
    private val programRepo: ProgramRepo
) {

    @Bean
    fun addPrograms() {
        val lines = File("src/main/resources/files/programs.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val values = it.split("|")
            programRepo.save(Program().apply {
                code = values[0]
                name = values[1]
                score = values[2]
            })
        }
        println("Programs saved")
    }

    @Bean
    fun addNews() {
        val count = mutableListOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        count.forEach {
            val fileContent = FileUtils.readFileToByteArray(File("/opt/uni3.jpg"))
            newsRepo.save(News().apply {
                date = "02.01.2021"
                title = "Новые горизонты $it"
                text = "Это действительно новые горизонты"
                photo = fileContent
            })
        }
        println("News saved")
    }

    @Bean
    fun addEvents() {
        val lines = File("src/main/resources/files/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val values = it.split("|")
            val fileContent = FileUtils.readFileToByteArray(File("/opt/uni3.jpg"))
            eventRepo.save(Event().apply {
                date = values[0]
                title = values[1]
                text = values[2]
                url = values[3]
                photo = fileContent
            })
        }
        println("Events saved")
    }
}