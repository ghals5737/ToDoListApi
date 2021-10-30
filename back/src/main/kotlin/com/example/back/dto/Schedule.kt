package com.example.back.dto

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime
import java.util.*
@Document(collation = "schedule")
data class Schedule(
    @Id
    var id: String? =null,
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: LocalTime?=null,
    var regUser: String? =null
)

