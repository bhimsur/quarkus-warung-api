package io.bhimsur.infra.web.mapper

import io.bhimsur.app.web.model.response.ErrorResponse
import io.bhimsur.domain.exception.BusinessException
import io.bhimsur.domain.exception.ValidationException
import io.bhimsur.domain.exception.WrongPasswordException
import java.util.function.Function
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider
import kotlin.reflect.KClass

@Provider
class BusinessExceptionMapper : ExceptionMapper<BusinessException> {

    private var exceptionMapper: Map<KClass<out BusinessException>, Function<BusinessException, Response>>

    init {
        exceptionMapper = configureExceptionMapper()
    }

    private fun configureExceptionMapper()
            : Map<KClass<out BusinessException>, Function<BusinessException, Response>> {
        val handlerMap = HashMap<KClass<out BusinessException>, Function<BusinessException, Response>>()
        handlerMap[WrongPasswordException::class] = Function { businessException -> unauthorized(businessException) }
        handlerMap[ValidationException::class] = Function { businessException -> badRequest(businessException) }
        return handlerMap
    }

    private fun unauthorized(businessException: BusinessException)
            : Response {
        return Response.ok(errorResponse(businessException))
            .status(Response.Status.UNAUTHORIZED.statusCode)
            .build()
    }

    private fun badRequest(businessException: BusinessException)
            : Response {
        return Response.ok(errorResponse(businessException))
            .status(Response.Status.BAD_REQUEST.statusCode)
            .build()
    }

    private fun errorResponse(businessException: BusinessException): ErrorResponse {
        return ErrorResponse(businessException.message)
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
    override fun toResponse(exception: BusinessException): Response {
        return exceptionMapper[exception::class]!!.apply(exception)
    }
}