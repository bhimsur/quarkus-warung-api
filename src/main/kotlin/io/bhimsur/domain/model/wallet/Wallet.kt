package io.bhimsur.domain.model.wallet

import io.bhimsur.domain.model.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Wallet(
    val id: UUID,
    val user: User,
    val amount: BigDecimal,
    val createDate: LocalDateTime,
    val modifiedDate: LocalDateTime
)
