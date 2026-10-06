package com.studymate.data

class OfflineSubjectRepository(private val subjectDao: SubjectDao): SubjectRepository {
    override suspend fun addSubject(subject: Subject) = subjectDao.addSubject(subject)
}