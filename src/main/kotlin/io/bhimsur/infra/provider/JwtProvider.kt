package io.bhimsur.infra.provider

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import io.bhimsur.infra.web.provider.TokenProvider
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class JwtProvider(
    @ConfigProperty(name = "jwt.issuer") private var issuer: String,
    @ConfigProperty(name = "jwt.secret") secret: String,
    @ConfigProperty(name = "jwt.expiration.time.minutes") private var expirationTimeInMinutes: Int
) : TokenProvider {

    private final val complementarySubscription: String = "complementary-subscription"
    private lateinit var algorithm: Algorithm
    private lateinit var jwtVerifier: JWTVerifier

    init {
        this.algorithm = Algorithm.HMAC512(secret)
        this.jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build()
    }

    override fun generateToken(subject: String): String {
        val builder: JWTCreator.Builder = JWT.create()
            .withIssuer(issuer)
            .withSubject(subject)
            .withIssuedAt(Date())
            .withClaim(complementarySubscription, UUID.randomUUID().toString())
            .withExpiresAt(plusMinutes(expirationTimeInMinutes))

        return builder.sign(algorithm)
    }

    override fun validateToken(token: String): DecodedJWT {
        return jwtVerifier.verify(token)
    }

    private fun plusMinutes(minutes: Int): Date {
        val oneMinutesInMillis: Long = TimeUnit.MINUTES.toMillis(1L)
        val calendar: Calendar = Calendar.getInstance()
        return Date.from(Instant.ofEpochMilli(calendar.timeInMillis + (minutes * oneMinutesInMillis)))
    }
}