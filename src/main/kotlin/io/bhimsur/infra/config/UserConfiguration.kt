package io.bhimsur.infra.config

import io.bhimsur.domain.feature.UserLogin
import io.bhimsur.domain.feature.UserRegistration
import io.bhimsur.domain.feature.impl.UserLoginImpl
import io.bhimsur.domain.feature.impl.UserRegistrationImpl
import io.bhimsur.domain.model.provider.CryptoProvider
import io.bhimsur.domain.model.provider.HashProvider
import io.bhimsur.domain.model.user.UserModelBuilder
import io.bhimsur.domain.model.user.UserRepository
import io.bhimsur.domain.validator.ModelValidator
import javax.enterprise.context.Dependent
import javax.inject.Named
import javax.inject.Singleton
import javax.validation.Validator
import javax.ws.rs.Produces

@Dependent
class UserConfiguration {
    @Produces
    @Singleton
    fun userLogin(userRepository: UserRepository, hashProvider: HashProvider, @Named("rsaProvider") cryptoProvider: CryptoProvider): UserLogin {
        return UserLoginImpl(hashProvider, userRepository, cryptoProvider)
    }

    @Produces
    @Singleton
    fun userRegistration(userRepository: UserRepository, hashProvider: HashProvider, userModelBuilder: UserModelBuilder): UserRegistration {
        return UserRegistrationImpl(userRepository, hashProvider, userModelBuilder)
    }

    @Produces
    @Singleton
    fun userModelBuilder(modelValidator: ModelValidator): UserModelBuilder {
        return UserModelBuilder(modelValidator)
    }

    @Produces
    @Singleton
    fun modelValidator(validator: Validator): ModelValidator {
        return ModelValidator(validator)
    }
}