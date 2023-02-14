package io.bhimsur.infra.provider

import io.bhimsur.domain.model.provider.CryptoProvider
import java.io.BufferedReader
import java.io.InputStream
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

@ApplicationScoped
@Named(value = "rsaProvider")
class RSAProvider : CryptoProvider {
    private val algorithm = "RSA"
    private val transformation = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding"
    override fun encrypt(plain: String, key: String): String {
        return ""
    }

    override fun encrypt(plain: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, preparePublicKey())
        return Base64.getEncoder().encodeToString(cipher.doFinal(plain.toByteArray(Charsets.UTF_8)))
    }

    override fun decrypt(encrypted: String, key: String): String {
        return ""
    }

    override fun decrypt(encrypted: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, preparePrivateKey())
        return String(cipher.doFinal(Base64.getDecoder().decode(encrypted)))
    }

    private fun preparePublicKey(): PublicKey {
        var key = loadKeyFile("security/rsa.public")
        key = key.replace("\\n", "")
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
        val data = Base64.getDecoder().decode(key.toByteArray(Charsets.UTF_8))
        val spec = X509EncodedKeySpec(data)
        val factory = KeyFactory.getInstance(algorithm)
        return factory.generatePublic(spec)
    }

    private fun preparePrivateKey(): PrivateKey {
        var key = loadKeyFile("security/rsa.private")
        key = key.replace("\\n", "")
            .replace("-----BEGIN RSA PRIVATE KEY-----", "")
            .replace("-----END RSA PRIVATE KEY-----", "")
        val data = Base64.getDecoder().decode(key.toByteArray(Charsets.UTF_8))
        val spec = PKCS8EncodedKeySpec(data)
        val factory = KeyFactory.getInstance(algorithm)
        return factory.generatePrivate(spec)
    }

    private fun loadKeyFile(path: String): String {
        val stored = ClassLoader.getSystemResourceAsStream(path) as InputStream
        val reader = BufferedReader(stored.reader())
        val key = StringBuilder()
        var line = reader.readLine()
        reader.use {
            while (line != null) {
                key.append(line)
                line = it.readLine()
            }
        }
        return key.toString()
    }

}