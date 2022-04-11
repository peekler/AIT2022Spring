package hu.ait.todorecyclerviewdemo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.todorecyclerviewdemo.ScrollingActivity
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.databinding.TodoDialogBinding
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(todo: Todo)

        fun todoUpdated(todo: Todo)
    }

    lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TodoHandler){
            todoHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface.")
        }
    }

    lateinit var binding: TodoDialogBinding

    private var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        // Are we in edit mode? - Have we received a Todo object to edit?
        if (arguments != null && requireArguments().containsKey(
                ScrollingActivity.KEY_TODO_EDIT)) {
            isEditMode = true
            dialogBuilder.setTitle("Edit Todo")
        } else {
            isEditMode = false
            dialogBuilder.setTitle("New Todo")
        }

        binding = TodoDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        // pre-fill the dialog if we are in edit mode
        if (isEditMode) {
            val todoToEdit =
                requireArguments().getSerializable(
                    ScrollingActivity.KEY_TODO_EDIT) as Todo

            binding.etTodoText.setText(todoToEdit.todoText)
            binding.cbTodoDone.isChecked = todoToEdit.isDone
        }

        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->
            if (isEditMode) {
                val todoToEdit =
                    (requireArguments().getSerializable(
                        ScrollingActivity.KEY_TODO_EDIT) as Todo).copy(
                        todoText = binding.etTodoText.text.toString(),
                        isDone =  binding.cbTodoDone.isChecked
                        )

                todoHandler.todoUpdated(todoToEdit)
            } else {
                todoHandler.todoCreated(
                    Todo(
                        null,
                        Date(System.currentTimeMillis()).toString(),
                        binding.cbTodoDone.isChecked,
                        binding.etTodoText.text.toString()
                    )
                )
            }
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }
        return dialogBuilder.create()
    }


}