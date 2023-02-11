package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper

class NotFoundException(message: String): BusinessException(ErrorMapper.Code.DATA_NOT_FOUND, message) {
    companion object {
        private const val serialVersionUID: Long = 6026432711264769521L
    }
}