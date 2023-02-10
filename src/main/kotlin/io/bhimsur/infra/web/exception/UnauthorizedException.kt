package io.bhimsur.infra.web.exception

import java.io.Serial

class UnauthorizedException : InfraException() {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -2204988514972107386L
    }
}