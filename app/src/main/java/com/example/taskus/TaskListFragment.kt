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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        This can have been done in onCreateView(), but this is better. According to StackOverFlow:
        "We face some crashes initializing view in onCreateView...You should inflate your layout
         in onCreateView but shouldn't initialize other views using findViewById in onCreateView
         Because sometimes view is not properly initialized. So always use findViewById
         in onViewCreated(when view is fully created) and it also passes the view as parameter.
         onViewCreated is a make sure that view is fully created. This gives subclasses a chance
          to initialize themselves once they know their view hierarchy has been completely created"
         */
        taskRecyclerView =
            view.findViewById(R.id.task_recycler_view) as RecyclerView
        taskRecyclerView.layoutManager = LinearLayoutManager(context)

        taskListViewModel.tasksListLiveData.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {tasks->
                updateUI(tasks)
            })
    }

    private fun updateUI(tasks: List<Task>) {
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