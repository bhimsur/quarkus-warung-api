package io.bhimsur.domain.model.wallet

import io.bhimsur.domain.model.constant.TransactionType
import java.math.BigDecimal

data class WalletTransactionInput(
    val amount: BigDecimal,
    val type: TransactionType
)