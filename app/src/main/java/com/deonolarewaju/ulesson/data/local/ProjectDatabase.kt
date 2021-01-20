package com.deonolarewaju.ulesson.data.local

import android.content.Context
import androidx.room.*
import com.deonolarewaju.ulesson.data.model.Chapter
import com.deonolarewaju.ulesson.data.model.RecentlyViewed
import com.deonolarewaju.ulesson.data.model.Subject
import com.deonolarewaju.ulesson.data.local.dao.RecentlyViewedDao
import com.deonolarewaju.ulesson.data.local.dao.SubjectDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
    Oyindamola Olarewaju Gideon
    deonolarewaju@gmail.com
 The sith coder
**/

@Database(
    entities = [Subject::class, RecentlyViewed::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converter::class)
abstract class ULessonDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao
    abstract fun recentViewDao(): RecentlyViewedDao

    companion object {
        private var instance: ULessonDatabase? = null

        fun getDatabase(context: Context): ULessonDatabase {
            if (instance == null) {
                synchronized(ULessonDatabase::class.java) {}
                instance =
                    Room.databaseBuilder(context, ULessonDatabase::class.java, "AppDatabase")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance!!
        }
    }
}

class Converter {
    @TypeConverter
    fun fromChapterList(chapterList: List<Chapter>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Chapter>>() {}.type
        return gson.toJson(chapterList, type)
    }

    @TypeConverter
    fun toChapterList(chapterStringList: String): List<Chapter> {
        val gson = Gson()
        val type = object : TypeToken<List<Chapter>>() {}.type
        return gson.fromJson(chapterStringList, type)
    }
}