package com.studymate.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SubjectDao {
    @Insert(entity = Subject1::class)
    suspend fun addSubject(subject: Subject1)
}