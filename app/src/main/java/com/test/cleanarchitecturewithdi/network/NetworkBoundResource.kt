package com.test.cleanarchitecturewithdi.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.test.cleanarchitecturewithdi.dependency_injection.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import com.test.cleanarchitecturewithdi.domains.Result
import com.test.cleanarchitecturewithdi.utils.ApiEmptyResponse
import com.test.cleanarchitecturewithdi.utils.ApiErrorResponse
import com.test.cleanarchitecturewithdi.utils.ApiResponse
import com.test.cleanarchitecturewithdi.utils.ApiSuccessResponse


@OptIn(ExperimentalCoroutinesApi::class)
abstract class NetworkBoundResource<ResultType, RequestType>(
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {
    suspend fun asFlow(): Flow<Result<ResultType>> {
        return loadFromDb().transformLatest { dbValue ->
            if (shouldFetch(dbValue)) {
                emit(Result.loading(dbValue))
                createCall().collect { apiResponse ->
                    when (apiResponse) {
                        is ApiSuccessResponse -> withContext(dispatcher) {
                            saveCallResult(processResponse(apiResponse))
                            emitAll(loadFromDb().mapLatest { Result.success(it) })
                        }
                        is ApiEmptyResponse -> emit(Result.success(dbValue))
                        is ApiErrorResponse -> {
                            onFetchFailed()
                            emit(Result.error(apiResponse.errorMessage, dbValue))
                        }
                    }
                }

            } else {
                emit(Result.success(dbValue))
            }
        }
    }

    protected open fun onFetchFailed() {
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract suspend fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flow<ResultType?>

    @MainThread
    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>
}
