package io.bhimsur.infra.config

import io.bhimsur.domain.feature.UserLogin
import io.bhimsur.domain.feature.UserRegistration
import io.bhimsur.domain.feature.impl.UserLoginImpl
import io.bhimsur.domain.feature.impl.UserRegistrationImpl
import io.bhimsur.domain.model.provider.HashProvider
import io.bhimsur.domain.model.user.UserModelBuilder
import io.bhimsur.domain.model.user.UserRepository
import javax.enterprise.context.Dependent
import javax.inject.Singleton
import javax.ws.rs.Produces

@Dependent
class UserConfiguration {
    @Produces
    @Singleton
    fun userLogin(userRepository: UserRepository, hashProvider: HashProvider): UserLogin {
        return UserLoginImpl(hashProvider, userRepository)
    }

    @Produces
    @Singleton
    fun userRegistration(userRepository: UserRepository, hashProvider: HashProvider, userModelBuilder: UserModelBuilder): UserRegistration {
        return UserRegistrationImpl(userRepository, hashProvider, userModelBuilder)
    }
}