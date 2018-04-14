package com.cluttered.cryptocurrency.security

import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SignatureException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HmacSHA256(secret: String) {

    private val mac: Mac

    init {
        val hmacSHA256 = "HmacSHA256"
        val secretBytes = secret.toByteArray()
        val secretKeySpec = SecretKeySpec(secretBytes, hmacSHA256)
        mac = Mac.getInstance(hmacSHA256)
        mac.init(secretKeySpec)
    }

    @Throws(SignatureException::class, NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun encode(message: String): String {
        return mac.doFinal(message.toByteArray())
                .map { String.format("%02x", it) }
                .reduce { acc, str -> acc + str }
    }
}