package io.bhimsur.infra.web.provider

import com.auth0.jwt.interfaces.DecodedJWT

interface TokenProvider {
    fun generateToken(subject: String): String
    fun validateToken(token: String): DecodedJWT
}