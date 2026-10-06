package com.studymate.data

interface SubjectRepository {
    suspend fun addSubject(subject: Subject)
}