package com.deonolarewaju.ulesson.repository.remote

import com.deonolarewaju.ulesson.repository.model.SubjectDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("content/grade")
    suspend fun getSubjectsData(): Response<SubjectDataResponse>
}