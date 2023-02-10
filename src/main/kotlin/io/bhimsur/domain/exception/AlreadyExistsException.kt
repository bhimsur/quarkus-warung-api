package io.bhimsur.domain.exception

import java.io.Serial

class AlreadyExistsException(code: Int, message: String) : BusinessException(code, message) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -4091218609242527318L
    }
}