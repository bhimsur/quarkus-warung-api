package io.bhimsur.domain.model.provider

interface CryptoProvider {
    fun encrypt(plain: String, key: String): String
    fun decrypt(encrypted: String, key: String): String
    fun encrypt(plain: String): String
    fun decrypt(encrypted: String): String
}