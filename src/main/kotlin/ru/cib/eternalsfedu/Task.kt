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

    @Value("\${path-jpg}")
    val path: String? = null
    private var fileContent: ByteArray? = null

    fun generateImagePaths(): MutableList<String> {
        val images = mutableListOf<String>()
        images.add("/opt/uni1.jpg")
        images.add("/opt/uni2.jpg")
        images.add("/opt/uni3.jpg")
        images.add("/opt/uni4.jpg")
        images.add("/opt/uni5.jpg")
//        images.add("D:\\фото\\uni1.jpg")
//        images.add("D:\\фото\\uni2.jpg")
//        images.add("D:\\фото\\uni3.jpg")
//        images.add("D:\\фото\\uni4.jpg")
//        images.add("D:\\фото\\uni5.jpg")
        return images
    }

    @Bean
    fun addPrograms() {
//        val lines = File("src/main/resources/files/programs.txt").readLines()
        val lines = File("/opt/programs.txt").readLines()
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

    /*
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
    }*/

    @Bean
    fun addNews() {
        val pathList = generateImagePaths()
//        val lines = File("src/main/resources/files/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        val lines = File("/opt/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val values = it.split("|")
            pathList.forEach { path ->
                fileContent = FileUtils.readFileToByteArray(File(path))
            }
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
        val pathList = generateImagePaths()
//        val lines = File("src/main/resources/files/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        val lines = File("/opt/events.txt").readLines(Charset.forName("WINDOWS-1251"))
        lines.forEach {
            val values = it.split("|")
            pathList.forEach { path ->
                fileContent = FileUtils.readFileToByteArray(File(path))
            }
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
//        val lines = File("src/main/resources/files/fio.txt").readLines()
        val lines = File("/opt/fio.txt").readLines()
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
//        val lines = File("src/main/resources/files/achivments.txt").readLines()
        val lines = File("/opt/achivments.txt").readLines()
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
//        val lines = File("src/main/resources/files/registration.txt").readLines()
        val lines = File("/opt/registration.txt").readLines()
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