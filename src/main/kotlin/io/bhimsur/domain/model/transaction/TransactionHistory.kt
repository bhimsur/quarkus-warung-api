package io.bhimsur.domain.model.transaction

import io.bhimsur.domain.model.constant.TransactionType
import io.bhimsur.domain.model.user.User
import io.bhimsur.domain.model.wallet.Wallet
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class TransactionHistory(
    val id: UUID,
    val wallet: Wallet,
    val user: User,
    val createDate: LocalDateTime,
    val amount: BigDecimal,
    val transactionType: TransactionType
)
