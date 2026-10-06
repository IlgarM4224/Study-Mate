package com.studymate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.studymate.util.Converters

@Database(entities = [Subject::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SubjectDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    companion object {
        @Volatile
        private var Instances: SubjectDatabase? = null

        fun getDatabase(context: Context): SubjectDatabase {
            return Instances ?:synchronized(this) {
                Room.databaseBuilder(context, SubjectDatabase::class.java, "stady_mate.db")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { Instances = it }
            }
        }
    }
}