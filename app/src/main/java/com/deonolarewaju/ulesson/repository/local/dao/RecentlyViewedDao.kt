package com.deonolarewaju.ulesson.repository.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deonolarewaju.ulesson.repository.model.RecentlyViewed

@Dao
interface RecentlyViewedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecentView(recentlyViewed: RecentlyViewed)

    @Query("SELECT * FROM recentlyviewed LIMIT :num")
    fun getRecentViews(num: Int): LiveData<List<RecentlyViewed>>
}