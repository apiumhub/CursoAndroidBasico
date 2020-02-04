package com.apiumhub.androidcourse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apiumhub.androidcourse.adapter.TodoItem

class TodoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_detail)
    }

    companion object {
        const val ARG_TODO_ITEM_ID = "arg.todo_item.id"

        fun getCallingIntent(context: Context, todoItem: TodoItem): Intent {
            return Intent(context, TodoDetailActivity::class.java).apply {
                putExtra(ARG_TODO_ITEM_ID, todoItem)
            }
        }
    }
}
