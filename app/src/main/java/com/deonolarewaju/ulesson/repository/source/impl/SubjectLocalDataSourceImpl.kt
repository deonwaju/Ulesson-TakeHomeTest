package com.deonolarewaju.ulesson.repository.source.impl

import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.repository.model.RecentlyViewed
import com.deonolarewaju.ulesson.repository.model.Subject
import com.deonolarewaju.ulesson.repository.local.SubjectLocalDataSource
import com.deonolarewaju.ulesson.repository.local.dao.RecentlyViewedDao
import com.deonolarewaju.ulesson.repository.local.dao.SubjectDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubjectLocalDataSourceImpl @Inject constructor(
    private val subjectDao: SubjectDao,
    private val recentlyViewedDao: RecentlyViewedDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SubjectLocalDataSource {

    override suspend fun saveSubjects(subjects: List<Subject>) = withContext(ioDispatcher) {
        subjectDao.saveSubjects(subjects)
    }

    override suspend fun saveRecentView(recentlyViewed: RecentlyViewed) = withContext(ioDispatcher) {
        recentlyViewedDao.saveRecentView(recentlyViewed)
    }

    override fun observeSubjects() = subjectDao.getAllSubjects()

    override suspend fun getSubject(id: Long) = withContext(ioDispatcher) {
        val subject = subjectDao.getSubject(id)
        return@withContext Resources.success(subject)
    }

    override fun observeRecentViews(limit: Int) = recentlyViewedDao.getRecentViews(limit)

}