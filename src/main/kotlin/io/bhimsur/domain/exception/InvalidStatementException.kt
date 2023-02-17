package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper

class InvalidStatementException(message: String) : BusinessException(ErrorMapper.Code.INVALID_PARAMETER, message) {
    companion object {
        private const val serialVersionUID: Long = 6216867323396725516L
    }

}