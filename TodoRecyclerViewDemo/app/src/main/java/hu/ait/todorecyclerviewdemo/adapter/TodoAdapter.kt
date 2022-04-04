package hu.ait.todorecyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecyclerviewdemo.R
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.databinding.TodoRowBinding
import hu.ait.todorecyclerviewdemo.touch.TodoTouchHelperCallback
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>,
    TodoTouchHelperCallback {

    var todoItems = mutableListOf<Todo>(
        Todo("2018. 09. 10", false, "Eat"),
        Todo("2018. 09. 11", false, "Drink")
    )
    val context : Context
    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoBinding = TodoRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(todoBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoItems[position]
        holder.bind(todo)

    }

    fun addTodo(newTodo: Todo) {
        todoItems.add(newTodo)
        notifyItemInserted(todoItems.lastIndex) // refreshes the recyclerview only where the new item was added

        //notifyDataSetChanged() - redraws the whole recyclerView
    }

    fun deleteLastItem() {
        todoItems.removeLast()
        notifyItemRemoved(todoItems.lastIndex+1)
    }

    fun deleteItem(idx: Int) {
        todoItems.removeAt(idx)
        notifyItemRemoved(idx)
    }

    override fun onDismissed(position: Int) {
        deleteItem(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
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
        }

    }

}