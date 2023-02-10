package io.bhimsur.domain.model.provider

interface HashProvider {
    fun hashPassword(password: String): String
    fun checkPassword(plainText: String, hashed: String): Boolean
}