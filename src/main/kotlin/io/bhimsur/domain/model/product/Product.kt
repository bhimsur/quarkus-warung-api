package io.bhimsur.domain.model.product

import io.bhimsur.domain.model.user.User
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class Product(
    val id: UUID,
    val user: User,
    val productName: String,
    val productImage: String,
    val description: String,
    val price: BigDecimal,
    val createDate: LocalDateTime,
    val modifiedDate: LocalDateTime,
    val deleted: Boolean
)
