package com.deonolarewaju.ulesson.data.local

import androidx.lifecycle.LiveData
import com.deonolarewaju.ulesson.util.helper.Resources
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject

interface SubjectLocalDataSource {
    suspend fun saveSubjects(subjects: List<Subject>)

    suspend fun saveRecentView(recentlyViewed: RecentlyViewed)

    fun observeSubjects(): LiveData<List<Subject>>

    suspend fun getSubject(id: Long): Resources<Subject>

    fun observeRecentViews(limit: Int): LiveData<List<RecentlyViewed>>

}