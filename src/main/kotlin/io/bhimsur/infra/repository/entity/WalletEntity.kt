package io.bhimsur.infra.repository.entity

import io.bhimsur.domain.model.wallet.Wallet
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "wallet")
class WalletEntity {
    @Id
    @Column(name = "id")
    lateinit var id: UUID
    @Column(name = "user_id")
    lateinit var userId: UUID
    @Column(name = "amount")
    lateinit var amount: BigDecimal
    @Column(name = "create_date")
    lateinit var createDate: LocalDateTime
    @Column(name = "modified_date")
    var modifiedDate: LocalDateTime? = null

    constructor(wallet: Wallet, topUp: Boolean) {
        id = wallet.id
        userId = wallet.userId
        if (topUp) topUp(wallet) else withdraw(wallet)
    }

    constructor()

    private fun topUp(wallet: Wallet) {
        amount += wallet.amount
        createDate = wallet.createDate
        modifiedDate = wallet.modifiedDate
        userId = wallet.userId
    }

    private fun withdraw(wallet: Wallet) {
        amount -= wallet.amount
        createDate = wallet.createDate
        modifiedDate = wallet.modifiedDate
        userId = wallet.userId
    }
}
