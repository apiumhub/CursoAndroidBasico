package com.apiumhub.androidcourse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apiumhub.androidcourse.adapter.TodoItem

class TodoDetailActivity : AppCompatActivity() {

    lateinit var descriptionTextView: TextView
    lateinit var saveButton: Button
    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_detail)

        descriptionTextView = findViewById(R.id.descriptionTextView)
        saveButton = findViewById(R.id.saveButton)
        calendarView = findViewById(R.id.calendarView)

        val todoItem: TodoItem? = intent.extras?.getParcelable(ARG_TODO_ITEM_ID)
        todoItem?.let {
            supportActionBar?.title = it.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val ARG_TODO_ITEM_ID = "arg.todo_item.id"

        fun getCallingIntent(context: Context, todoItem: TodoItem): Intent {
            return Intent(context, TodoDetailActivity::class.java).apply {
                putExtra(ARG_TODO_ITEM_ID, todoItem)
            }
        }
    }
}
