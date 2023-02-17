package io.bhimsur.domain.model.wallet

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Wallet(
    val id: UUID,
    val userId: UUID,
    val amount: BigDecimal,
    val createDate: LocalDateTime,
    val modifiedDate: LocalDateTime?
)
