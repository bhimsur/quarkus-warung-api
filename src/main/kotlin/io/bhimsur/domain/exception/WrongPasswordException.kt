package io.bhimsur.domain.exception

import java.io.Serial

class WrongPasswordException() : BusinessException(1, "Wrong Password") {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 2202259816080522452L
    }
}