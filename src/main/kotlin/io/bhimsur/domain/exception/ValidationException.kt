package io.bhimsur.domain.exception

import java.io.Serial

class ValidationException(messages: List<String>) : BusinessException(0, messages) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -3560537771684717288L
    }
}