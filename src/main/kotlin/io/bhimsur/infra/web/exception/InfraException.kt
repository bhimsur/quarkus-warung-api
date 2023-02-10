package io.bhimsur.infra.web.exception

import java.io.Serial

open class InfraException: RuntimeException() {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -4538168308098313369L
    }
}