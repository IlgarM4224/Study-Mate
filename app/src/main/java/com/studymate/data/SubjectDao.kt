package com.studymate.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Insert(entity = Subject::class)
    suspend fun addSubject(subject: Subject)

    @Query("SELECT * from subject")
    fun getAllSubjects(): Flow<List<Subject>>

    @Query("SELECT * FROM subject WHERE id = :id")
    fun getSubjectById(id: Int): Flow<Subject>

    @Update
    suspend fun updateSubject(subject: Subject)
}