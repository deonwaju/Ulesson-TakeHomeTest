package com.deonolarewaju.ulesson.repository.remote

import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.repository.model.SubjectDataResponse

interface APIRemoteDataSource {
    suspend fun getSubjectsData(): Resources<SubjectDataResponse>
}