package io.bhimsur.domain.exception


class AlreadyExistsException(code: Int, message: String) : BusinessException(code, message) {
    companion object {
        private const val serialVersionUID: Long = -4091218609242527318L
    }
}