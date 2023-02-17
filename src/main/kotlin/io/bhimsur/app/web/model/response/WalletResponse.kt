package io.bhimsur.app.web.model.response

import com.fasterxml.jackson.annotation.JsonRootName
import io.bhimsur.domain.model.wallet.Wallet
import io.quarkus.runtime.annotations.RegisterForReflection
import java.math.BigDecimal

@JsonRootName("wallet")
@RegisterForReflection
data class WalletResponse(
    val amount: BigDecimal
) {
    constructor(wallet: Wallet) : this(wallet.amount)
}
