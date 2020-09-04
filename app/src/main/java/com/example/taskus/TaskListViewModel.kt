package com.example.taskus

import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {

    val tasks = mutableListOf<Task>()

    init {
        for (i in 0 until 100) {
            val task = Task()
            task.title = "task #$i"
            tasks += task
        }
    }
}