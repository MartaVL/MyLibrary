package com.example.mylibrary.database

import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "loans")
data class Loan (
    @NotNull val idBook: Int = 0,
    @NotNull val contact: String = "",
    @NotNull val date: Date
) {

    @PrimaryKey(autoGenerate = true)
    @NotNull var id: Int = 0

    @Ignore
    var nameBook: String = ""


}