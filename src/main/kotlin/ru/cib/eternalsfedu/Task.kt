package ru.cib.eternalsfedu

import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.domain.*
import ru.cib.eternalsfedu.repository.*
import ru.cib.eternalsfedu.utils.SNILS
import java.io.File
import java.nio.charset.Charset
import java.util.*

@Service
@EnableScheduling
class Task(
    private val newsRepo: NewsRepo,
    private val eventRepo: EventRepo,
    private val programRepo: ProgramRepo,
    private val rankRepo: RankRepo,
    private val achivmentRepo: AchivmentRepo,
    private val registrationRepo: RegistrationRepo,
) {
    private var fileContent: ByteArray? = null

    fun generateImagePaths(): String? {
        val r = Random()
        val low = 0
        val high = 4
//        return when (r.nextInt(high - low) + low) {
//            0 -> "/opt/uni1.jpg"
//            1 -> "/opt/uni2.jpg"
//            2 -> "/opt/uni3.jpg"
//            3 -> "/opt/uni4.jpg"
//            4 -> "/opt/uni5.jpg"
//            else -> null
//        }
        return when (r.nextInt(high - low) + low) {
            0 -> "D:\\фото\\uni1.jpg"
            1 -> "D:\\фото\\uni2.jpg"
            2 -> "D:\\фото\\uni3.jpg"
            3 -> "D:\\фото\\uni4.jpg"
            4 -> "D:\\фото\\uni5.jpg"
            else -> null
        }
    }

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
                midRange = values[3]
            })
        }
        println("Programs saved")
    }

    @Bean
    fun addNews() {
        val lines = File("src/main/resources/files/events.txt").readLines(Charset.forName("WINDOWS-1251"))
//        val lines = File("/opt/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val path = generateImagePaths()
            val values = it.split("|")
            fileContent = FileUtils.readFileToByteArray(File(path!!))
            newsRepo.save(News().apply {
                title = values[1]
                description = values[2]
                image = fileContent
            })
        }
        println("News saved")
    }

    @Bean
    fun addEvents() {
        val lines = File("src/main/resources/files/events.txt").readLines(Charset.forName("WINDOWS-1251"))
//        val lines = File("/opt/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val path = generateImagePaths()
            val values = it.split("|")
                fileContent = FileUtils.readFileToByteArray(File(path!!))
            eventRepo.save(Event().apply {
                date = values[0]
                title = values[1]
                description = values[2]
                url = values[3]
                image = fileContent
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
                snils = SNILS.Generate()
                score = values[1].toLong()
            })
        }
        println("Rank saved")
    }

    @Bean
    fun addAchivment() {
        val lines = File("src/main/resources/files/achivments.txt").readLines()
//        val lines = File("/opt/achivments.txt").readLines()
        lines.forEach {
            val values = it.split("|")
            achivmentRepo.save(Achivment().apply {
                name = values[0]
                text = values[1]
            })
        }
        println("Achivments saved")
    }

    @Bean
    fun addRegistration() {
        val lines = File("src/main/resources/files/registration.txt").readLines()
//        val lines = File("/opt/registration.txt").readLines()
        lines.forEach {
            val values = it.split("|")
            registrationRepo.save(Registration().apply {
                username = values[0]
                fio = values[1]
                email = values[2]
                phone = values[3]
                snils = values[4]
                password = values[5]
            })
        }
        println("Registration saved")
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