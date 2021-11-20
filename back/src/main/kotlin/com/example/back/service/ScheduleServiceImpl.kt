package com.example.back.service

import com.example.back.dto.Schedule
import com.example.back.dto.ScheduleReq
import com.example.back.dto.ScheduleRes
import com.example.back.repository.ScheduleRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScheduleServiceImpl(
    val scheduleRepository:ScheduleRepository
) : ScheduleService
{
    override fun createShcedule(scheduleReq: ScheduleReq): ScheduleRes {
        var schedule=Schedule().apply {
            this.id= ObjectId.getSmallestWithDate(Date())
            this.userId=scheduleReq.userId
            this.title=scheduleReq.title
            this.des=scheduleReq.des
            this.regDt= Date()
        }
        schedule=scheduleRepository.save(schedule)
        val scheduleRes=ScheduleRes().apply {
            this.id=schedule.id.toString()
            this.userId=schedule.userId
            this.title=schedule.title
            this.des=schedule.des
            this.regDt=schedule.regDt
        }
        return scheduleRes;
    }

    override fun findScheduleByUserId(userId:String): List<ScheduleRes> {
        val scheduleResList= mutableListOf<ScheduleRes>();
        scheduleRepository.findByUserId(userId).forEach{
            scheduleResList.add(
                ScheduleRes().apply {
                    this.id = it.id.toString()
                    this.userId=it.userId
                    this.title=it.title
                    this.des=it.des
                    this.regDt=it.regDt
                }
            )
        }
        return scheduleResList;
    }

    override fun updateScheduleById(schedule: Schedule,id: ObjectId): Schedule {
        if(scheduleRepository.existsScheduleById(id)){
            schedule.id=id
            return scheduleRepository.save(schedule)
        }else{
            return Schedule()
        }
    }

    override fun findByUserIdAndTitleLike(userId: String, title: String): List<ScheduleRes> {
        val scheduleResList= mutableListOf<ScheduleRes>();
        scheduleRepository.findByUserIdAndTitleLike(userId,title).forEach{
            scheduleResList.add(
                ScheduleRes().apply {
                    this.id = it.id.toString()
                    this.userId=it.userId
                    this.title=it.title
                    this.des=it.des
                    this.regDt=it.regDt
                }
            )
        }
        return scheduleResList;
    }
}