package com.example.mylibrary.database

import android.content.Context
import androidx.room.*

@Database(entities = [Book::class, Loan::class], version = 6)
@TypeConverters(Converters:: class)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun loanDao(): LoanDao

    companion object {
        private const val DATABASE_NAME = "library"
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(context: Context): LibraryDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE
        }
    }
}