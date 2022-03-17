package hu.ait.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.layoutinflaterdemo.databinding.ActivityMainBinding
import hu.ait.layoutinflaterdemo.databinding.TodoRowBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            addNewTodoItem()
        }
    }

    private fun addNewTodoItem() {
        val todoRow = TodoRowBinding.inflate(layoutInflater)

        todoRow.tvTodoTitle.text = binding.etTodo.text.toString()

        todoRow.btnDelete.setOnClickListener {
            binding.layoutContent.removeView(todoRow.root)
        }

        if (binding.cbImportant.isChecked) {
            todoRow.ivIcon.setImageResource(R.drawable.important)
        } else {
            todoRow.ivIcon.setImageResource(R.mipmap.ic_launcher)
        }


        binding.layoutContent.addView(todoRow.root)
    }
}