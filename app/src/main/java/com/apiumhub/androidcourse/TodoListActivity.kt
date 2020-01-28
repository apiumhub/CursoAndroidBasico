package com.apiumhub.androidcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiumhub.androidcourse.adapter.TodoAdapter
import com.apiumhub.androidcourse.adapter.TodoItem

class TodoListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var viewLayoutManager: RecyclerView.LayoutManager
    private lateinit var todoAdapter: TodoAdapter
    private val data: MutableList<TodoItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewLayoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(data)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewLayoutManager
            adapter = todoAdapter
        }
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
