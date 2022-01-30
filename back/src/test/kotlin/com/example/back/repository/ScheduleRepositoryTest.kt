package com.example.back.repository

import com.example.back.dto.Schedule
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
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

    private lateinit var expected: Schedule

    @DisplayName("결과로 테스트 할 객체 초기화")
    @BeforeEach
    fun initialize() {

        val time = Date(528021800000)
        expected = Schedule().apply {
            this.id = ObjectId.getSmallestWithDate(time)
            this.userId = "test"
            this.title = "test_title"
            this.doneYn = true
            this.des = "test_des"
            this.regDt = time
        }

        // 데이터 삽입도 딱 한번만 해야 문제가 없음
        val result = scheduleRepository.save(expected)
        assertEquals(expected, result)
    }

    @DisplayName("userId가 test인 scheduleList를 조회하면 그 결과의 첫번째 schedule은 예상한 테스트 결과와 같다.")
    @Test
    fun findByUserIdTest() {
        val scheduleList = scheduleRepository.findByUserId("test")
        val result = scheduleList[0]
        assertEquals(expected, result)
    }

    @DisplayName("userId가 test이고 title이 test_title인 scheduleList를 조회하면 그 결과의 첫번째 schedule은 예상한 테스트 결과와 같다.")
    @Test
    fun findByUserIdAndTitleLikeTest() {
        val scheduleList = scheduleRepository.findByUserIdAndTitleLike("test", "test_title")
        val result = scheduleList[0]
        assertEquals(expected, result)
    }

    @DisplayName("userId가 test인 scheduleList를 조회한 그 결과 첫번째 schedule의 id는 존재한다.")
    @Test
    fun existsScheduleByIdTest() {
        val scheduleList = scheduleRepository.findByUserId("test")
        val schedule = scheduleList[0]
        val result = scheduleRepository.existsScheduleById(schedule.id)
        assertEquals(true, result)
    }

    /*
        삭제하는 테스트는 한번에 테스트할 때 테스트 코드가 순서 없이 실행되기 때문에 실패할 수도 성공할 수도 있다.

    @Test
    fun deleteTest() {
        val scheduleList = scheduleRepository.findByUserId("test")
        val result = scheduleList[0]
        scheduleRepository.delete(result)
        assertEquals(false, scheduleRepository.existsScheduleById(result.id))
    }
     */
}