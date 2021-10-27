package com.example.back.dto

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Document(collation = "schedule")
data class Schedule(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? =null,
    var userId: String? =null,
    var title: String? =null,
    var des: String? =null,
    var regDt: Date,
    var regUser: String? =null
)


