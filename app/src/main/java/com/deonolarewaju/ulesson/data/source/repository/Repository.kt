package com.deonolarewaju.ulesson.data.source.repository

import androidx.lifecycle.LiveData
import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject

interface Repository {

    fun fetchSubjects(): LiveData<Resources<Unit>>

    fun getSubjects(): LiveData<List<Subject>>

    fun getRecentViews(limit: Int): LiveData<List<RecentlyViewed>>

    suspend fun getSubject(id: Long): Resources<Subject>

    suspend fun saveRecentView(recentlyViewed: RecentlyViewed)

    suspend fun saveSubjects(subjects: List<Subject>)
}