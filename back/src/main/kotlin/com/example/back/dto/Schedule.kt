package com.example.back.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*



@Document(collation = "schedule")
data class Schedule(
    @Id
    var id: Long? =null,
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: Date,
    var regUser: String? =null
)


