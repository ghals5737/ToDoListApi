package com.example.back.dto

import lombok.Data
import lombok.NoArgsConstructor
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "shcedule")
data class Schedule(
    @Id
    var id: ObjectId? = ObjectId.get(),
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: Date?=null
)

data class ScheduleReq(
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
)

data class ScheduleRes(
    var id:String?=null,
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: Date?=null
)

