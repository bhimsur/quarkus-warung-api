package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper

class WrongPasswordException() : BusinessException(ErrorMapper.Code.WRONG_PASSWORD, ErrorMapper.Message.WRONG_PASSWORD) {
    companion object {
        private const val serialVersionUID: Long = 2202259816080522452L
    }
}