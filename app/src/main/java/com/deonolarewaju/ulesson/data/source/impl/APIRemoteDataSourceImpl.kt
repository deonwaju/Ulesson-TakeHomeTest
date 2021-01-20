package com.deonolarewaju.ulesson.data.source.impl

import com.deonolarewaju.ulesson.util.Resources
import com.deonolarewaju.ulesson.data.model.SubjectDataResponse
import com.deonolarewaju.ulesson.data.remote.APIRemoteDataSource
import com.deonolarewaju.ulesson.data.remote.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class APIRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : APIRemoteDataSource {
    override suspend fun getSubjectsData(): Resources<SubjectDataResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = apiService.getSubjectsData()
                if (response.isSuccessful) {
                    Resources.success(response.body()!!)
                } else {
                    Resources.error(response.errorBody().toString(), null)
                }
            } catch (e: Exception) {
                Resources.error(e.message!!, null)
            } catch (IO: Exception) {
                Resources.error("No internet access", null)
            }
        }
}