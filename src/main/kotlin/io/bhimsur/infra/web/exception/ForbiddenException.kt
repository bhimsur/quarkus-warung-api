package io.bhimsur.infra.web.exception

import java.io.Serial

class ForbiddenException: InfraException() {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 9093724024704330070L
    }
}