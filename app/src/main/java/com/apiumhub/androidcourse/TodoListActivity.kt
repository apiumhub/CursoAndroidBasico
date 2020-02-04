package com.apiumhub.androidcourse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiumhub.androidcourse.TodoDetailActivity.Companion.ARG_TODO_ITEM_ID
import com.apiumhub.androidcourse.adapter.TodoAdapter
import com.apiumhub.androidcourse.adapter.TodoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var viewLayoutManager: RecyclerView.LayoutManager
    private lateinit var todoAdapter: TodoAdapter
    private val tasks: MutableList<TodoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ToDo App"

        viewLayoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(tasks, ::onClick)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewLayoutManager
            adapter = todoAdapter
        }

        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, (viewLayoutManager as LinearLayoutManager).orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        fab = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            startActivityForResult(TodoDetailActivity.getCallingIntent(this), DETAIL_REQUEST_CODE)
        }
    }

    private fun onClick(todoItem: TodoItem) {
        startActivityForResult(TodoDetailActivity.getCallingIntent(this, todoItem), DETAIL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == DETAIL_REQUEST_CODE) {
            val todoItem: TodoItem? = data.extras?.getParcelable<TodoItem>(ARG_TODO_ITEM_ID)
            todoItem?.let { newTodo ->
                val find: TodoItem? = tasks.find { it.id == newTodo.id }
                if (find != null) {
                    tasks.remove(find)
                }
                tasks.addAll(listOf(newTodo))
                tasks.sortBy { it.date }
                todoAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val DETAIL_REQUEST_CODE = 1000
    }
}
