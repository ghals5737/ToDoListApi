package com.example.back.repository

import com.example.back.dto.Schedule
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@DataMongoTest
class ScheduleRepositoryTest {

    @Autowired
    lateinit var scheduleRepository: ScheduleRepository

    @Test
    fun saveTest(){
        val schedule= Schedule().apply {
            this.id= ObjectId.getSmallestWithDate(Date())
            this.userId="test"
            this.title="test_title"
            this.doneYn=true
            this.des="test_des"
            this.regDt= Date()
        }
        val result=scheduleRepository.save(schedule)
        Assertions.assertNotNull(result.id)
        Assertions.assertEquals("test", result.userId)
        Assertions.assertEquals("test_title", result.title)
        Assertions.assertEquals(true, result.doneYn)
        Assertions.assertEquals("test_des", result.des)
        Assertions.assertNotNull(result.regDt)
    }

    @Test
    fun findByUserIdTest(){
        val scheduleList=scheduleRepository.findByUserId("test")
        val result= scheduleList[0]
        Assertions.assertNotNull(result.id)
        Assertions.assertEquals("test", result.userId)
        Assertions.assertEquals("test_title", result.title)
        Assertions.assertEquals(true, result.doneYn)
        Assertions.assertEquals("test_des", result.des)
        Assertions.assertNotNull(result.regDt)
    }

    @Test
    fun findByUserIdAndTitleLikeTest(){
        val scheduleList=scheduleRepository.findByUserIdAndTitleLike("test","test_title")
        val result= scheduleList[0]
        Assertions.assertNotNull(result.id)
        Assertions.assertEquals("test", result.userId)
        Assertions.assertEquals("test_title", result.title)
        Assertions.assertEquals(true, result.doneYn)
        Assertions.assertEquals("test_des", result.des)
        Assertions.assertNotNull(result.regDt)
    }

    @Test
    fun existsScheduleByIdTest(){
        val scheduleList=scheduleRepository.findByUserId("test")
        val result= scheduleList[0]
        Assertions.assertEquals(true, scheduleRepository.existsScheduleById(result.id))
    }

    @Test
    fun deleteTest(){
        val scheduleList=scheduleRepository.findByUserId("test")
        val result= scheduleList[0]
        scheduleRepository.delete(result)
        Assertions.assertEquals(false, scheduleRepository.existsScheduleById(result.id))
    }
}