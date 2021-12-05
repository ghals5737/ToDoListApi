package com.example.back.dto

import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "schedule")
data class Schedule(
    @Id
    var id: ObjectId? = ObjectId.get(),
    var userId: String? =null,
    var title: String? =null,
    var doneYn: Boolean?= false,
    var des: String? =null,
    var regDt: Date?=null
)

data class ScheduleReq(
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var doneYn: Boolean?= false,
)

data class ScheduleRes(
    var id:String?=null,
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var doneYn: Boolean?= false,
    var regDt: Date?=null
)

fun scheduleReqToSchedule(scheduleReq: ScheduleReq):Schedule{
    return Schedule().apply {
        this.id= ObjectId.getSmallestWithDate(Date())
        this.userId=scheduleReq.userId
        this.title=scheduleReq.title
        this.doneYn=scheduleReq.doneYn
        this.des=scheduleReq.des
        this.regDt= Date()
    }
}

fun scheduleToScheduleRes(schedule: Schedule):ScheduleRes{
    return ScheduleRes().apply {
        this.id=schedule.id.toString()
        this.userId=schedule.userId
        this.title=schedule.title
        this.doneYn=schedule.doneYn
        this.des=schedule.des
        this.regDt=schedule.regDt
    }
}

