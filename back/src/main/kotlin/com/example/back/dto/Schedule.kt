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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Schedule

        if (userId != other.userId) return false
        if (title != other.title) return false
        if (doneYn != other.doneYn) return false
        if (des != other.des) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (doneYn?.hashCode() ?: 0)
        result = 31 * result + (des?.hashCode() ?: 0)
        return result
    }
}

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

