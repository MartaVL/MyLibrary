package com.example.mylibrary.database

import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "books")
data class Book(
    @NotNull val name: String = "",
    @NotNull val author: String = "",
    @NotNull val genre: String = "",
    var loan: Boolean = false,
    var read: Boolean = false,
    var score: Int = 0) {

    @PrimaryKey(autoGenerate = true)
    @NotNull var id: Int = 0


    override fun toString() : String {
        var str = SpannableStringBuilder()
            .bold { append(name) }
            .append("\n\t\t")
            .italic { append(author) }

        return str.toString()
    }
}