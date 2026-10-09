package com.studymate.data

import kotlinx.coroutines.flow.Flow

class OfflineSubjectRepository(private val subjectDao: SubjectDao): SubjectRepository {
    override suspend fun addSubject(subject: Subject) = subjectDao.addSubject(subject)
    override fun getAllSubjectsStream(): Flow<List<Subject>> = subjectDao.getAllSubjects()
    override fun getSubjectByIdStream(id: Int): Flow<Subject?> = subjectDao.getSubjectById(id)
    override suspend fun updateSubject(subject: Subject) = subjectDao.updateSubject(subject)
    override suspend fun deleteSubject(subject: Subject) = subjectDao.deleteSubject(subject)
}