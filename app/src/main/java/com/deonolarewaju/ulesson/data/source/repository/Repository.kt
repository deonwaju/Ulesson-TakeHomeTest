package com.deonolarewaju.ulesson.data.source.repository

import androidx.lifecycle.LiveData
import com.deonolarewaju.ulesson.util.helper.Resources
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject

interface Repository {

    fun fetchSubjects(): LiveData<Resources<Unit>>

    fun getSubjects(): LiveData<List<Subject>>

    suspend fun saveRecentView(recentlyViewed: RecentlyViewed)

    fun getRecentViews(limit: Int): LiveData<List<RecentlyViewed>>

    suspend fun saveSubjects(subjects: List<Subject>)

    suspend fun getSubject(id: Long): Resources<Subject>
}