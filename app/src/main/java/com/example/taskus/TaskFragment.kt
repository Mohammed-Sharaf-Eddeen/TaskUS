package com.example.taskus

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

private const val TITLE_FIELD = "task title data field"

class TaskFragment: Fragment() {
    private lateinit var task: Task
    private lateinit var titleField: EditText
    private lateinit var deadlineButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = Task()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        titleField = view.findViewById(R.id.task_title) as EditText
        deadlineButton = view.findViewById(R.id.task_deadline) as Button
        deadlineButton.apply {
            text = task.deadline.toString()
            isEnabled = false
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        titleField.addTextChangedListener(TaskTextListener(TITLE_FIELD))
    }

     private inner class TaskTextListener(val taskDataField: String): TextWatcher{
         init {
             taskDataField
         }

         override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (taskDataField == TITLE_FIELD) {
                task.title = s.toString()
            }
        }

    }
}