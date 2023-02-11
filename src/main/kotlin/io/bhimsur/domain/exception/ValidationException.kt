package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper
import java.io.Serial

class ValidationException(messages: List<String>) : BusinessException(ErrorMapper.Code.VALIDATION, messages) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -3560537771684717288L
    }
}