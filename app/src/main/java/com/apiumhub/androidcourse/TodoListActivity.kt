package com.apiumhub.androidcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiumhub.androidcourse.adapter.TodoAdapter
import com.apiumhub.androidcourse.adapter.TodoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class TodoListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var viewLayoutManager: RecyclerView.LayoutManager
    private lateinit var todoAdapter: TodoAdapter
    private val data: MutableList<TodoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ToDo App"

        viewLayoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(data, ::onClick)

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
            val randomNumber = Random(System.currentTimeMillis()).nextInt()
            val todoItem = TodoItem("Elemento $randomNumber", System.currentTimeMillis())
            data.add(todoItem)
            todoAdapter.notifyDataSetChanged()
        }
    }

    private fun onClick(todoItem: TodoItem) {
        startActivity(TodoDetailActivity.getCallingIntent(this, todoItem))
    }

    override fun onResume() {
        super.onResume()

        val elements: Array<TodoItem> = arrayOf(
            TodoItem("Elemento 1", System.currentTimeMillis()),
            TodoItem("Elemento 2", System.currentTimeMillis()),
            TodoItem("Elemento 3", System.currentTimeMillis())
        )
        data.addAll(elements)
        todoAdapter.notifyDataSetChanged()
    }
}
