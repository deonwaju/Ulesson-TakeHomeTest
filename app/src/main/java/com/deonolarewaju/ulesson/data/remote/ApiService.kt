package com.deonolarewaju.ulesson.data.remote

import com.deonolarewaju.ulesson.data.model.SubjectDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("content/grade")
    suspend fun getSubjectsData(): Response<SubjectDataResponse>
}