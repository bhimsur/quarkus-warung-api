package io.bhimsur.domain.exception

import java.util.*

open class BusinessException : RuntimeException {

    private val code: Int
    private val messages: List<String>

    constructor(code: Int, message: String) : super(message) {
        this.code = code
        messages = LinkedList(listOf(message))
    }

    constructor(code: Int, messages: List<String>) : super(messages.joinToString(separator = ", ")) {
        this.code = code
        this.messages = messages
    }

    companion object {
        private const val serialVersionUID: Long = 8736432437215848069L
    }
}
