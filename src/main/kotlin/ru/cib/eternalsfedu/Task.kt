package ru.cib.eternalsfedu

import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.domain.Event
import ru.cib.eternalsfedu.domain.News
import ru.cib.eternalsfedu.domain.Program
import ru.cib.eternalsfedu.domain.Rank
import ru.cib.eternalsfedu.repository.EventRepo
import ru.cib.eternalsfedu.repository.NewsRepo
import ru.cib.eternalsfedu.repository.ProgramRepo
import ru.cib.eternalsfedu.repository.RankRepo
import java.io.File
import java.nio.charset.Charset
import java.util.*

@Service
@EnableScheduling
class Task(
    private val newsRepo: NewsRepo,
    private val eventRepo: EventRepo,
    private val programRepo: ProgramRepo,
    private val rankRepo: RankRepo
) {

    @Value("\${path-jpg}")
    val path: String? = null

    @Bean
    fun addPrograms() {
        val lines = File("src/main/resources/files/programs.txt").readLines()
//        val lines = File("/opt/programs.txt").readLines()
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
            val fileContent = FileUtils.readFileToByteArray(File(path!!))
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
//        val lines = File("/opt/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val values = it.split("|")
            val fileContent = FileUtils.readFileToByteArray(File(path!!))
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

    @Bean
    fun addRank() {
        val lines = File("src/main/resources/files/fio.txt").readLines()
//        val lines = File("/opt/fio.txt").readLines()
        lines.forEach {
            val values = it.split("|")
            rankRepo.save(Rank().apply {
                fio = values[0]
                score = values[1].toLong()
            })
        }
        println("Rank saved")
    }

    @Scheduled(fixedDelay = 5000)
    fun updateRank() {
        val rank = rankRepo.findAll().shuffled().last()
        val r = Random()
        val low = -10
        val high = 10
        val result = r.nextInt(high - low) + low
        rank.score = rank.score?.plus(result)
        rankRepo.save(rank)
    }
}