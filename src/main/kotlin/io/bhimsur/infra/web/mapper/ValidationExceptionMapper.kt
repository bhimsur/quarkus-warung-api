package io.bhimsur.infra.web.mapper

import io.bhimsur.app.web.model.response.ErrorResponse
import javax.validation.ConstraintViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ValidationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
    /**
     * Map an exception to a [javax.ws.rs.core.Response]. Returning
     * `null` results in a [javax.ws.rs.core.Response.Status.NO_CONTENT]
     * response. Throwing a runtime exception results in a
     * [javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR] response.
     *
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    override fun toResponse(exception: ConstraintViolationException?): Response {
        val errorResponse = ErrorResponse()
        exception!!.constraintViolations
            .iterator()
            .forEachRemaining { errorResponse.body.add(it.message) }
        return Response.ok().status(Response.Status.BAD_REQUEST.statusCode).build()
    }
}