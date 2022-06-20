package com.umbrellait.unspot.core_network_impl

import java.math.BigInteger
import java.security.MessageDigest

class QueryHelper {

    fun getTsApiKeyMd5(): Triple<String, String, String> {
        val timeStamp = (System.currentTimeMillis() / 1000).toString()
        val input = timeStamp + PRIVATE_API_KEY + PUBLIC_API_KEY
        val hash = md5(input)
        return Triple(timeStamp, PUBLIC_API_KEY, hash)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    companion object {
        private const val PUBLIC_API_KEY = "716954d731b0d752b3acd64f1b24c421"
        private const val PRIVATE_API_KEY = "0e4cb378b84d6363c97a2ea7326e8eb50027caa0"
    }

}
