package com.deonolarewaju.ulesson.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.deonolarewaju.ulesson.util.helper.Resources
import com.deonolarewaju.ulesson.util.helper.Resources.*
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject
import com.deonolarewaju.ulesson.data.local.SubjectLocalDataSource
import com.deonolarewaju.ulesson.data.remote.APIRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ProjectRepositoryImpl(
    private val remoteDataSource: APIRemoteDataSource,
    private val localDataSource: SubjectLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Repository {

    override fun fetchSubjects(): LiveData<Resources<Unit>> =
        liveData(ioDispatcher) {
            val response = remoteDataSource.getSubjectData()
            when (response.status) {
                Status.LOADING -> {
                    emit(Resources.loading((Unit)))
                }
                Status.SUCCESS -> {
                    response.data?.let { subjectData ->
                        localDataSource.saveSubjects(subjectData.data.subjects)
                    }
                    emit(Resources.success(Unit))
                }
                Status.ERROR -> {
                    emit(Resources.error(response.message!!, null))
                }
            }
        }

    override fun getSubjects() = localDataSource.observeSubjects()

    override suspend fun saveRecentView(recentlyViewed: RecentlyViewed) {
        localDataSource.saveRecentView(recentlyViewed)
    }

    override fun getRecentViews(limit: Int) = localDataSource.observeRecentViews(limit)

    override suspend fun saveSubjects(subjects: List<Subject>) {
        localDataSource.saveSubjects(subjects)
    }

    override suspend fun getSubject(id: Long): Resources<Subject> {
        return localDataSource.getSubject(id)
    }

}