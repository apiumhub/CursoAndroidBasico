package com.apiumhub.androidcourse

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apiumhub.androidcourse.adapter.TodoItem
import java.util.Calendar
import kotlin.random.Random

class TodoDetailActivity : AppCompatActivity() {

    lateinit var tileTextView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var saveButton: Button
    lateinit var calendarView: CalendarView

    private var originalTodoItem: TodoItem? = null
    private var selectedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_detail)

        tileTextView = findViewById(R.id.tileTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        saveButton = findViewById(R.id.saveButton)
        calendarView = findViewById(R.id.calendarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        originalTodoItem = intent.extras?.getParcelable(ARG_TODO_ITEM_ID)

        if (originalTodoItem != null) { //EDIT MODE
            supportActionBar?.title = originalTodoItem?.title + " - ${originalTodoItem?.id}"

            tileTextView.text = originalTodoItem?.title
            descriptionTextView.text = originalTodoItem?.description
            calendarView.date = originalTodoItem?.date ?: System.currentTimeMillis()
        } else { //CREATION MODE
            supportActionBar?.title = "Añadir nueva tarea"
        }

        saveButton.setOnClickListener {
            val title: String = tileTextView.text.toString()
            val description: String = descriptionTextView.text.toString()
            val date: Long = if (selectedTime != 0L) {
                selectedTime
            } else {
                calendarView.date
            }

            val newTodoItem: TodoItem = if (originalTodoItem != null) {
                TodoItem(originalTodoItem?.id!!, title, description, date)
            } else {
                TodoItem(Random(System.currentTimeMillis()).nextInt(0, 100000), title, description, date)
            }

            val intent: Intent = Intent().apply {
                putExtra(ARG_TODO_ITEM_ID, newTodoItem)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar: Calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedTime = calendar.time.time
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
        const val ARG_TODO_ITEM_ID = "arg.todo_item.id"

        fun getCallingIntent(context: Context, todoItem: TodoItem? = null): Intent =
            Intent(context, TodoDetailActivity::class.java).apply {
                putExtra(ARG_TODO_ITEM_ID, todoItem)
            }
    }
}
