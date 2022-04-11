package hu.ait.todorecyclerviewdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todotable")
    fun getAllTodos() : LiveData<List<Todo>>

    @Insert
    fun insertTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)


}