package com.test.cleanarchitecturewithdi.repositories

import com.test.cleanarchitecturewithdi.dependency_injection.IoDispatcher
import com.test.cleanarchitecturewithdi.domains.Result
import com.test.cleanarchitecturewithdi.models.SignIn
import com.test.cleanarchitecturewithdi.models.SignInResponse
import com.test.cleanarchitecturewithdi.network.NetworkResource
import com.test.cleanarchitecturewithdi.services.ApiServices
import com.test.cleanarchitecturewithdi.utils.ApiResponse
import com.test.cleanarchitecturewithdi.utils.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignInRepository @Inject constructor(
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    val apiService: ApiServices,
) {
    private val controlledRunner = ControlledRunner<Flow<Result<SignInResponse>>>()

    suspend fun signIn(signIn: SignIn): Flow<Result<SignInResponse>> {
        return controlledRunner.joinPreviousOrRun {
            object : NetworkResource<SignInResponse>(dispatcher) {
                override suspend fun saveCallResult(data: SignInResponse) {
                    super.saveCallResult(data)
                }

                override suspend fun createCall(): Flow<ApiResponse<SignInResponse>> {
                    return apiService.userAuthentication(signIn)
                }
            }.asFlow()
        }
    }
}
