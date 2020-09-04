package com.example.taskus

import java.util.*

data class Task(val id: UUID = UUID.randomUUID(),
                var title: String = "",
                var deadline: Date = Date()
                )