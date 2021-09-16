package com.test.cleanarchitecturewithdi.domains

import com.test.cleanarchitecturewithdi.dependency_injection.IoDispatcher
import com.test.cleanarchitecturewithdi.models.SignIn
import com.test.cleanarchitecturewithdi.models.SignInResponse
import com.test.cleanarchitecturewithdi.repositories.SignInRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignInUseCase @Inject constructor (
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val signInRepository: SignInRepository
) : FlowUseCase<SignIn, SignInResponse>(ioDispatcher) {
    override suspend fun execute(parameters: SignIn): Flow<Result<SignInResponse>> {
        return signInRepository.signIn(parameters)
    }
}
