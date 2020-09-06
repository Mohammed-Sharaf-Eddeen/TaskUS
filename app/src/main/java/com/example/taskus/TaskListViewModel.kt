package com.example.taskus

import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()

    val tasksListLiveData = taskRepository.getTasks()





}