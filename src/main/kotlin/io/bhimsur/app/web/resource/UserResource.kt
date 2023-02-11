package io.bhimsur.app.web.resource

import io.bhimsur.app.web.model.constant.RequestMapper
import io.bhimsur.app.web.model.constant.ResourceConstant
import io.bhimsur.app.web.model.request.UserLoginRequest
import io.bhimsur.app.web.model.request.UserRegistrationRequest
import io.bhimsur.app.web.model.response.UserResponse
import io.bhimsur.domain.exception.WrongPasswordException
import io.bhimsur.domain.feature.UserLogin
import io.bhimsur.domain.feature.UserRegistration
import io.bhimsur.domain.model.user.User
import io.bhimsur.infra.web.exception.UnauthorizedException
import io.bhimsur.infra.web.provider.TokenProvider
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path(RequestMapper.USER)
class UserResource(
    private val userRegistration: UserRegistration,
    private val userLogin: UserLogin,
    private val tokenProvider: TokenProvider
) {
    @POST
    @Path(RequestMapper.REGISTER)
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun register(
        @Valid @NotNull userRegistrationRequest: UserRegistrationRequest
    ): Response {
        val user: User = userRegistration.command(userRegistrationRequest.toUserRegistrationInput())
        val token: String = tokenProvider.generateToken(user.id.toString())
        return Response
            .ok(UserResponse(user))
            .status(Response.Status.CREATED)
            .header(ResourceConstant.ACCESS_TOKEN, token)
            .build()
    }

    @POST
    @Path(RequestMapper.LOGIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun login(
        @Valid @NotNull loginRequest: UserLoginRequest
    ): Response {
        val user: User
        try {
            user = userLogin.command(loginRequest.toUserLoginInput())
        } catch (e: WrongPasswordException) {
            throw UnauthorizedException()
        }
        val token = tokenProvider.generateToken(user.id.toString())
        return Response
            .ok(UserResponse(user))
            .status(Response.Status.OK)
            .header(ResourceConstant.ACCESS_TOKEN, token)
            .build()
    }
}