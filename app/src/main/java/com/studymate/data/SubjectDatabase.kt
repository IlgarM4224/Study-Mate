package com.studymate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subject1::class], version = 1, exportSchema = false)
abstract class SubjectDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    companion object {
        @Volatile
        private var Instances: SubjectDatabase? = null

        fun getDatabase(context: Context): SubjectDatabase {
            return Instances ?:synchronized(this) {
                Room.databaseBuilder(context, SubjectDatabase::class.java, "stady_mate.db")
                    .build()
                    .also { Instances = it }
            }
        }
    }
}