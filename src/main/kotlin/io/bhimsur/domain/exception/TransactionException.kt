package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper

class TransactionException(message: String) : BusinessException(ErrorMapper.Code.TRANSACTION_FAILED, message) {
    companion object {
        private const val serialVersionUID: Long = 914905632332976074L
    }
}