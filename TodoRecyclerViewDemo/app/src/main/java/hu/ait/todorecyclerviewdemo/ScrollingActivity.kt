package hu.ait.todorecyclerviewdemo

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.ait.todorecyclerviewdemo.adapter.TodoAdapter
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.databinding.ActivityScrollingBinding
import hu.ait.todorecyclerviewdemo.dialog.TodoDialog
import hu.ait.todorecyclerviewdemo.touch.TodoReyclerTouchCallback

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.fab.setOnClickListener { view ->
            //adapter.addTodo(Todo("2022. 03. 21.",false,"Demo"))

            TodoDialog().show(supportFragmentManager,"TODO_DIALOG")
        }

        adapter = TodoAdapter(this)
        binding.recyclerTodo.adapter = adapter

        //val decorator =  DividerItemDecoration(this,
        //    DividerItemDecoration.VERTICAL)
        //binding.recyclerTodo.addItemDecoration(decorator)

        //binding.recyclerTodo.layoutManager = GridLayoutManager(this, 2)

        val touchCallbakList = TodoReyclerTouchCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbakList)
        itemTouchHelper.attachToRecyclerView(binding.recyclerTodo)
    }


    override fun todoCreated(todo: Todo) {
        adapter.addTodo(todo)

        Snackbar.make(binding.root, "Todo created",Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                adapter.deleteLastItem()
            }
            .show()
    }

}