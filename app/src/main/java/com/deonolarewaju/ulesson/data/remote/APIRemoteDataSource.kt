package com.deonolarewaju.ulesson.data.remote

import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.data.model.SubjectDataResponse

interface APIRemoteDataSource {
    suspend fun getSubjectsData(): Resources<SubjectDataResponse>
}