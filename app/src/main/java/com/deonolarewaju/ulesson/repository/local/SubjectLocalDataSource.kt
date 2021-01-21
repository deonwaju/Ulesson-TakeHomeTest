package com.deonolarewaju.ulesson.repository.local

import androidx.lifecycle.LiveData
import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.repository.model.RecentlyViewed
import com.deonolarewaju.ulesson.repository.model.Subject

interface SubjectLocalDataSource {
    suspend fun saveSubjects(subjects: List<Subject>)

    suspend fun saveRecentView(recentlyViewed: RecentlyViewed)

    fun observeSubjects(): LiveData<List<Subject>>

    suspend fun getSubject(id: Long): Resources<Subject>

    fun observeRecentViews(limit: Int): LiveData<List<RecentlyViewed>>

}