package hu.ait.todorecyclerviewdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecyclerviewdemo.R
import hu.ait.todorecyclerviewdemo.ScrollingActivity
import hu.ait.todorecyclerviewdemo.data.AppDatabase
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.databinding.TodoRowBinding
import hu.ait.todorecyclerviewdemo.touch.TodoTouchHelperCallback
import java.util.*
import kotlin.concurrent.thread

class TodoAdapter(var context: Context)
    : ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffCallback()),
      TodoTouchHelperCallback {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoBinding = TodoRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(todoBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)

    }

    fun deleteLastItem() {
        //todoItems.removeLast()
        //notifyItemRemoved(todoItems.lastIndex+1)
    }

    fun deleteItem(idx: Int) {
        thread{
            AppDatabase.getInstance(context).todoDao().deleteTodo(getItem(idx))
        }
    }

    override fun onDismissed(position: Int) {
        deleteItem(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(var binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvDate.text = todo.createData
            binding.cbDone.text = todo.todoText
            binding.cbDone.isChecked = todo.isDone

            binding.btnDelete.setOnClickListener {
                deleteItem(this.adapterPosition)
            }

            binding.btnEdit.setOnClickListener {
                (context as ScrollingActivity).showEditDialog(
                    getItem(this.adapterPosition)
                )
            }

            binding.cbDone.setOnClickListener {
                val currentTodo = getItem(adapterPosition)
                currentTodo.isDone = binding.cbDone.isChecked

                thread {
                    AppDatabase.getInstance(context).todoDao().updateTodo(currentTodo)
                }
            }
        }

    }
}


class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.todoid == newItem.todoid
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}
