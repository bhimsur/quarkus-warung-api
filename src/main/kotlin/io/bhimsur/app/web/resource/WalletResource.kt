package io.bhimsur.app.web.resource

import io.bhimsur.app.web.model.constant.RequestMapper
import io.bhimsur.app.web.model.request.WalletTransactionRequest
import io.bhimsur.app.web.model.response.WalletResponse
import io.bhimsur.domain.feature.GetWalletByUser
import io.bhimsur.domain.feature.WalletTransaction
import io.bhimsur.infra.web.provider.TokenProvider
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response

@Path(RequestMapper.WALLET)
class WalletResource(
    private val getWalletByUser: GetWalletByUser,
    private val tokenProvider: TokenProvider,
    private val walletTransaction: WalletTransaction
) {
    @GET
    fun getWalletByUser(@Context httpHeaders: HttpHeaders): Response {
        val authorization = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION)[0]
        val response = getWalletByUser.command(getId(authorization))
        return Response.ok(WalletResponse(response))
            .status(Response.Status.OK)
            .build()
    }

    @PUT
    @Path(RequestMapper.WALLET_TRANSACTION)
    fun walletTransaction(
        @Valid @NotNull request: WalletTransactionRequest,
        @Context httpHeaders: HttpHeaders
    ): Response {
        val authorization = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION)[0]
        val response = walletTransaction.command(request.toWalletTransactionInput(), getId(authorization))
        return Response.ok(response)
            .status(Response.Status.OK)
            .build()
    }

    private fun getId(token: String): UUID {
        val decoded = tokenProvider.validateToken(token)
        return UUID.fromString(decoded.subject)
    }
}