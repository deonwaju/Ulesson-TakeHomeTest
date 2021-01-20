package com.deonolarewaju.ulesson.data.remote

import com.deonolarewaju.ulesson.util.helper.Resources
import com.deonolarewaju.ulesson.data.model.SubjectDataResponse

interface APIRemoteDataSource {
    suspend fun getSubjectData(): Resources<SubjectDataResponse>
}