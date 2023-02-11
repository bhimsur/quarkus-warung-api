package io.bhimsur.domain.exception

import io.bhimsur.domain.model.constant.ErrorMapper
import java.io.Serial

class WrongPasswordException() : BusinessException(ErrorMapper.Code.WRONG_PASSWORD, ErrorMapper.Message.WRONG_PASSWORD) {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 2202259816080522452L
    }
}