package hu.ait.todorecyclerviewdemo

import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.ait.todorecyclerviewdemo.adapter.TodoAdapter
import hu.ait.todorecyclerviewdemo.data.AppDatabase
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.databinding.ActivityScrollingBinding
import hu.ait.todorecyclerviewdemo.dialog.TodoDialog
import hu.ait.todorecyclerviewdemo.touch.TodoReyclerTouchCallback
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import kotlin.concurrent.thread

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    companion object {
        const val KEY_TODO_EDIT = "KEY_TODO_EDIT"
        const val PREFNAME = "MYPREFERENCES"
        const val KEY_WAS_STARTED = "KEY_WAS_STARTED"
    }


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

            TodoDialog().show(supportFragmentManager, "TODO_DIALOG")
        }

        initRecyclerView()

        if (!wasStartedBefore()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(binding.fab)
                .setPrimaryText("New Todo")
                .setSecondaryText("Click here to add new items")
                .show()

            saveAppWasStarted()
        }
    }

    fun saveAppWasStarted() {
        val sharedpref = getSharedPreferences(
            PREFNAME,
            MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.putBoolean(KEY_WAS_STARTED, true)
        editor.apply()
    }

    fun wasStartedBefore() : Boolean {
        val sharedPref = getSharedPreferences(PREFNAME, MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_WAS_STARTED, false)
    }


    private fun initRecyclerView() {
        adapter = TodoAdapter(this)
        binding.recyclerTodo.adapter = adapter

        val touchCallbakList = TodoReyclerTouchCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbakList)
        itemTouchHelper.attachToRecyclerView(binding.recyclerTodo)

        val todoItems = AppDatabase.getInstance(this).todoDao().getAllTodos()
        todoItems.observe(this, Observer { items ->
                adapter.submitList(items)
            })
    }

    fun showEditDialog(todoToEdit: Todo) {
        val dialog = TodoDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_TODO_EDIT, todoToEdit)
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "TAG_ITEM_EDIT")
    }

    override fun todoCreated(todo: Todo) {
        thread {
            AppDatabase.getInstance(this).todoDao().insertTodo(todo)

            runOnUiThread {
                Snackbar.make(binding.root, "Todo created", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        adapter.deleteLastItem()
                    }
                    .show()
            }
        }
    }

    override fun todoUpdated(todo: Todo) {
        thread {
            AppDatabase.getInstance(this).todoDao().updateTodo(todo)
        }
    }

}