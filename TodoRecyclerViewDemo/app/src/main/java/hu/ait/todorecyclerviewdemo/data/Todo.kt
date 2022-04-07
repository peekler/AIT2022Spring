package hu.ait.todorecyclerviewdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todotable")
data class Todo(
    @PrimaryKey(autoGenerate = true) val todoid: Long?,
    @ColumnInfo(name = "createDate") var createData: String,
    @ColumnInfo(name = "isDone") var isDone: Boolean,
    @ColumnInfo(name = "todoText") var todoText: String
)