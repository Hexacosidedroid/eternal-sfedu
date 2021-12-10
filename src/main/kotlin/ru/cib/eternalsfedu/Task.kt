package ru.cib.eternalsfedu

import org.apache.commons.io.FileUtils
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.cib.eternalsfedu.domain.Event
import ru.cib.eternalsfedu.domain.News
import ru.cib.eternalsfedu.repository.EventRepo
import ru.cib.eternalsfedu.repository.NewsRepo
import java.io.File
import java.util.*

@EnableScheduling
@Service
class Task(
    private val newsRepo: NewsRepo,
    private val eventRepo: EventRepo
) {

    //    @Scheduled(fixedDelay = 600000)
    fun addNews() {
        val count = mutableListOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        val listOfNews = mutableListOf<News>()
        count.forEach {
            listOfNews.add(News().apply {
                date = ""
                title = "Новые горизонты $it"
                text = "Это действительно новые горизонты"
//                photo =
            })
        }
        newsRepo.saveAll(listOfNews)
        println("News сохранились")
    }

//    @Scheduled(fixedDelay = 60000)
    fun addEvents() {
        println("Events сохранение")
        val count = mutableListOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        count.forEach {
            val fileContent = FileUtils.readFileToByteArray(File("D:\\фото\\uni5.jpg"))
//            val encodedPhoto = Base64.getEncoder().encodeToString(fileContent)
            eventRepo.save(Event().apply {
                date = "01.01.2021"
                title = "Это мероприятие $it"
                text = "Это действительно новые мероприятия"
                photo = fileContent
            })
            println("Events сохранились")
        }
    }
}