package io.bhimsur.infra.config

import io.bhimsur.domain.feature.GetWalletByUser
import io.bhimsur.domain.feature.impl.GetWalletByUserImpl
import io.bhimsur.domain.model.wallet.WalletModelBuilder
import io.bhimsur.domain.model.wallet.WalletRepository
import io.bhimsur.domain.validator.ModelValidator
import javax.enterprise.context.Dependent
import javax.inject.Singleton
import javax.ws.rs.Produces

@Dependent
class WalletConfiguration {
    @Produces
    @Singleton
    fun walletModelBuilder(modelValidator: ModelValidator): WalletModelBuilder {
        return WalletModelBuilder(modelValidator)
    }

    @Produces
    @Singleton
    fun getWalletByUser(walletRepository: WalletRepository): GetWalletByUser {
        return GetWalletByUserImpl(walletRepository)
    }
}