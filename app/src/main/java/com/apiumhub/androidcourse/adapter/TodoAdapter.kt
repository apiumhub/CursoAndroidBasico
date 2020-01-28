package com.apiumhub.androidcourse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apiumhub.androidcourse.R
import kotlinx.android.synthetic.main.row_element.view.*
import java.text.SimpleDateFormat
import java.util.Date

class TodoAdapter(private val data: List<TodoItem>) : RecyclerView.Adapter<TodoViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_element, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoItem: TodoItem = data[position]
        holder.itemView.title.text = todoItem.title
        holder.itemView.date.text = dateFormat.format(Date(todoItem.date))
    }
}