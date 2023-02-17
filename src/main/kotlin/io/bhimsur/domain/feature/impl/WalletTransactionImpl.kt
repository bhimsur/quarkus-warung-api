package io.bhimsur.domain.feature.impl

import io.bhimsur.app.web.model.response.WalletResponse
import io.bhimsur.domain.exception.InvalidStatementException
import io.bhimsur.domain.exception.NotFoundException
import io.bhimsur.domain.feature.WalletTransaction
import io.bhimsur.domain.model.constant.TransactionType
import io.bhimsur.domain.model.wallet.WalletModelBuilder
import io.bhimsur.domain.model.wallet.WalletRepository
import io.bhimsur.domain.model.wallet.WalletTransactionInput
import java.util.*

class WalletTransactionImpl(
    private val walletRepository: WalletRepository,
    private val walletModelBuilder: WalletModelBuilder
) : WalletTransaction {
    override fun command(request: WalletTransactionInput, userId: UUID): WalletResponse {
        val walletOpt = walletRepository.findByUserId(userId)
        val wallet = walletOpt.orElseThrow { NotFoundException("User Not Found") }
        val response = if (TransactionType.TOP_UP == request.type) {
            walletRepository.topUp(walletModelBuilder.build(wallet.id, userId, request.amount))
        } else if (TransactionType.WITHDRAW == request.type) {
            walletRepository.withdraw(walletModelBuilder.build(wallet.id, userId, request.amount))
        } else {
            throw InvalidStatementException("Type Not Allowed")
        }
        return WalletResponse(response.amount)
    }
}