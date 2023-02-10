package io.bhimsur.infra.web.mapper

import io.bhimsur.app.web.model.response.ErrorResponse
import io.bhimsur.infra.web.exception.ForbiddenException
import io.bhimsur.infra.web.exception.InfraException
import io.bhimsur.infra.web.exception.UnauthorizedException
import java.util.function.Function
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider
import kotlin.reflect.KClass

@Provider
class InfraExceptionMapper : ExceptionMapper<InfraException> {

    private var exceptionMapper: Map<KClass<out InfraException>, Function<InfraException, Response>>

    init {
        exceptionMapper = configureExceptionMapper()
    }

    private fun configureExceptionMapper()
            : Map<KClass<out InfraException>, Function<InfraException, Response>> {
        val handlerMap = HashMap<KClass<out InfraException>, Function<InfraException, Response>>()
        handlerMap[ForbiddenException::class] = Function { forbidden() }
        handlerMap[UnauthorizedException::class] = Function { unauthorized() }
        return handlerMap
    }

    private fun forbidden(): Response {
        return Response.ok(errorResponse(Response.Status.FORBIDDEN.toString()))
            .status(Response.Status.FORBIDDEN.statusCode)
            .build()
    }

    private fun unauthorized(): Response {
        return Response.ok(errorResponse(Response.Status.UNAUTHORIZED.toString()))
            .status(Response.Status.UNAUTHORIZED.statusCode)
            .build()
    }

    private fun errorResponse(message: String): ErrorResponse {
        return ErrorResponse(message)
    }

    /**
     * Map an exception to a [javax.ws.rs.core.Response]. Returning
     * `null` results in a [javax.ws.rs.core.Response.Status.NO_CONTENT]
     * response. Throwing a runtime exception results in a
     * [javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR] response.
     *
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    override fun toResponse(exception: InfraException): Response {
        return exceptionMapper[exception::class]!!.apply(exception)
    }
}