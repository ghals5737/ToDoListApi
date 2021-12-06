package com.example.back.service

import com.example.back.dto.*
import com.example.back.repository.ScheduleRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScheduleServiceImpl(
    val scheduleRepository:ScheduleRepository
) : ScheduleService
{
    override fun createSchedule(scheduleReq: ScheduleReq): ScheduleRes {
        var schedule=scheduleRepository.save(scheduleReqToSchedule(scheduleReq))
        return scheduleToScheduleRes(schedule);
    }

    override fun findScheduleByUserId(userId:String): List<ScheduleRes> {
        val scheduleResList= mutableListOf<ScheduleRes>();
        scheduleRepository.findByUserId(userId).forEach{
            scheduleResList.add(
                scheduleToScheduleRes(it)
            )
        }
        return scheduleResList;
    }

    override fun updateScheduleById(scheduleReq: ScheduleReq,id: ObjectId): ScheduleRes {
        if(scheduleRepository.existsScheduleById(id)){
            var schedule=scheduleRepository.save(scheduleReqToSchedule(scheduleReq))
            return scheduleToScheduleRes(schedule);
        }else{
            return ScheduleRes()
        }
    }

    override fun findByUserIdAndTitleLike(userId: String, title: String): List<ScheduleRes> {
        val scheduleResList= mutableListOf<ScheduleRes>();
        scheduleRepository.findByUserIdAndTitleLike(userId,title).forEach{
            scheduleResList.add(
                scheduleToScheduleRes(it)
            )
        }
        return scheduleResList;
    }

    override fun deleteScheduleById(scheduleReq: ScheduleReq, id: ObjectId): ScheduleRes {
        var schedule= scheduleReqToSchedule(scheduleReq)
        scheduleRepository.delete(schedule)
        return scheduleToScheduleRes(schedule);
    }
}