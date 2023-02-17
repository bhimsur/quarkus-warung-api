package io.bhimsur.app.web.model.request

import io.bhimsur.domain.model.constant.TransactionType
import io.bhimsur.domain.model.wallet.WalletTransactionInput
import java.math.BigDecimal
import javax.validation.constraints.NotBlank

data class WalletTransactionRequest(
    @NotBlank
    val amount: BigDecimal,
    @NotBlank
    val type: TransactionType
) {
    fun toWalletTransactionInput(): WalletTransactionInput {
        return WalletTransactionInput(amount, type)
    }
}