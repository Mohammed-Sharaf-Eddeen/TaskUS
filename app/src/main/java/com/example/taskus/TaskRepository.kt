package com.example.taskus

import android.content.Context
import androidx.room.Room
import com.example.taskus.database.TaskDatabase
import java.util.*

private const val DATABASE_NAME = "task-database"

class TaskRepository private constructor(context: Context){

    companion object{
        private var INSTANSE: TaskRepository? = null

        fun initialize(context: Context){
            if (INSTANSE == null) {
                INSTANSE = TaskRepository(context)
            }
        }

        fun get(): TaskRepository{
            return INSTANSE
                ?: throw IllegalStateException("TaskRepository must be initialize first!")
        }
    }

    private val database : TaskDatabase = Room.databaseBuilder(
        context.applicationContext,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val taskDao = database.taskDao()

    fun getTasks() = taskDao.getTasks()
    fun getTask(ID: UUID) = taskDao.getTask(ID)

}