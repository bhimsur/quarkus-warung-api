package io.bhimsur.domain.model.wallet

import io.bhimsur.domain.validator.ModelValidator
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Named

@Named
class WalletModelBuilder(private val modelValidator: ModelValidator) {
    fun build(id: UUID, userId: UUID, amount: BigDecimal): Wallet {
        return modelValidator.validate(Wallet(id, userId, amount, LocalDateTime.now(), null))
    }
}