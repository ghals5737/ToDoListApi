package com.example.back.service

import com.example.back.dto.*
import com.example.back.repository.ScheduleRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class ScheduleServiceImpl(
    val scheduleRepository: ScheduleRepository
) : ScheduleService {
    override fun createSchedule(scheduleReq: ScheduleReq): ScheduleRes {
        val schedule = scheduleRepository.save(scheduleReqToSchedule(scheduleReq))
        return scheduleToScheduleRes(schedule)
    }

    override fun findScheduleByUserId(userId: String): List<ScheduleRes> {
        val scheduleResList = mutableListOf<ScheduleRes>()
        scheduleRepository.findByUserId(userId).forEach {
            scheduleResList.add(
                scheduleToScheduleRes(it)
            )
        }
        return scheduleResList
    }

    override fun updateScheduleById(scheduleReq: ScheduleReq, id: ObjectId): ScheduleRes {
        return if (scheduleRepository.existsScheduleById(id)) {
            val schedule = scheduleReqToSchedule(scheduleReq)
            schedule.id = id
            scheduleToScheduleRes(scheduleRepository.save(schedule))
        } else {
            ScheduleRes()
        }
    }

    override fun findByUserIdAndTitleLike(userId: String, title: String): List<ScheduleRes> {
        val scheduleResList = mutableListOf<ScheduleRes>()
        scheduleRepository.findByUserIdAndTitleLike(userId, title).forEach {
            scheduleResList.add(
                scheduleToScheduleRes(it)
            )
        }
        return scheduleResList
    }

    override fun deleteScheduleById(id: ObjectId): ScheduleRes {
        return if (scheduleRepository.existsScheduleById(id)) {
            val schedule = scheduleRepository.findById(id)
            scheduleRepository.delete(schedule)
            scheduleToScheduleRes(schedule)
        } else {
            ScheduleRes()
        }
    }
}