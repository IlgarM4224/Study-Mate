package com.studymate.data

import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    suspend fun addSubject(subject: Subject)
    fun getAllSubjectsStream(): Flow<List<Subject>>
    fun getSubjectByIdStream(id: Int): Flow<Subject?>
    suspend fun updateSubject(subject: Subject)
    suspend fun deleteSubject(subject: Subject)
}