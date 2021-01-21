package com.deonolarewaju.ulesson.repository.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.deonolarewaju.ulesson.repository.local.dao.RecentlyViewedDao
import com.deonolarewaju.ulesson.repository.local.dao.SubjectDao
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class ProjectDatabaseSetup {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: ULessonDatabase
    protected lateinit var subjectDao: SubjectDao
    protected lateinit var recentDao: RecentlyViewedDao

    @Before
    fun init() {
        db = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            ULessonDatabase::class.java
        ).build()

        subjectDao = db.subjectDao()
        recentDao = db.recentViewDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

}