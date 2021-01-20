package com.deonolarewaju.ulesson.di

import android.content.Context
import com.deonolarewaju.ulesson.BuildConfig
import com.deonolarewaju.ulesson.BuildConfig.BASE_URL
import com.deonolarewaju.ulesson.data.local.SubjectLocalDataSource
import com.deonolarewaju.ulesson.data.source.impl.SubjectLocalDataSourceImpl
import com.deonolarewaju.ulesson.data.local.ULessonDatabase
import com.deonolarewaju.ulesson.data.local.dao.RecentlyViewedDao
import com.deonolarewaju.ulesson.data.local.dao.SubjectDao
import com.deonolarewaju.ulesson.data.remote.APIRemoteDataSource
import com.deonolarewaju.ulesson.data.source.impl.APIRemoteDataSourceImpl
import com.deonolarewaju.ulesson.data.remote.ApiService
import com.deonolarewaju.ulesson.data.source.repository.ProjectRepositoryImpl
import com.deonolarewaju.ulesson.data.source.repository.Repository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesSubjectDao(@ApplicationContext context: Context): SubjectDao {
        return ULessonDatabase.getDatabase(context).subjectDao()
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(service: ApiService): APIRemoteDataSource {
        return APIRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun providesRepository(
        remoteSource: APIRemoteDataSource,
        localSource: SubjectLocalDataSource
    ): Repository {
        return ProjectRepositoryImpl(remoteSource, localSource)
    }

    @Provides
    @Singleton
    fun providesRecentViewDao(@ApplicationContext context: Context): RecentlyViewedDao {
        return ULessonDatabase.getDatabase(context).recentViewDao()
    }

    @Provides
    @Singleton
    fun providesSubjectService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(
        subjectDao: SubjectDao, recentlyViewedDao: RecentlyViewedDao
    ): SubjectLocalDataSource {
        return SubjectLocalDataSourceImpl(subjectDao, recentlyViewedDao)
    }

}
