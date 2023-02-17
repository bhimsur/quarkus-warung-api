package io.bhimsur.domain.feature

import io.bhimsur.app.web.model.response.WalletResponse
import io.bhimsur.domain.model.wallet.WalletTransactionInput
import java.util.*

interface WalletTransaction {
    fun command(request: WalletTransactionInput, userId: UUID): WalletResponse
}