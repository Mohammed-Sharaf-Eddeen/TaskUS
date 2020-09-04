package com.example.taskus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class TaskListFragment : Fragment() {

    private lateinit var taskRecyclerView: RecyclerView
    private val taskListViewModel: TaskListViewModel by lazy {
        ViewModelProvider(this).get(TaskListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_task_list, container, false)
        taskRecyclerView =
            view.findViewById(R.id.task_recycler_view) as RecyclerView
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()

        return view
    }

    private fun updateUI() {
        val tasks = taskListViewModel.tasks
        taskRecyclerView.adapter = TaskAdapter(tasks)
    }

    companion object {
        fun newInstance(): TaskListFragment {
            return TaskListFragment()
        }
    }

    private inner class TaskHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.task_title)
        private val deadlineTextView: TextView = itemView.findViewById(R.id.task_deadline)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(task: Task){
            titleTextView.text = task.title
            deadlineTextView.text = task.deadline.toString()
        }

        override fun onClick(v: View?) {
        }
    }

    private inner class TaskAdapter(var tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : TaskHolder {
            val view = layoutInflater.inflate(
                R.layout.fragment_task_list_item, parent, false)
            return TaskHolder(view)
        }

        override fun getItemCount() = tasks.size

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = tasks[position]
            holder.bind(task)
        }
    }
}