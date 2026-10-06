package com.studymate.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface SubjectDao {
    @Insert(entity = Subject::class)
    suspend fun addSubject(subject: Subject)
}