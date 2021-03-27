package com.uzett.oneneeslip.es.plugins

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeGroupMessages
import java.security.MessageDigest
import java.util.*


fun Bot.addressGen() {
    eventChannel.subscribeGroupMessages {
        case ("#myaddr", trim = true, ignoreCase = true) {
            val pubKey =
                """-----BEGIN PUBLIC KEY-----
                    |MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALnMsMz9xpp11OruhNrh/+82rz0uh9Tn
                    |O43WOhVVM3pBUMt9m28cb++EU9q7EuIao7AN/k6Q3UIvJ3st4rh3Um8CAwEAAQ==
                    |-----END PUBLIC KEY-----
                """.trimMargin()
            val pk_md5 = md5(pubKey).substring(0,1)
            val userAddr = "pk_md5_begin1=${pk_md5}&gid=${sender.group.id}&sid=${sender.id}"
            val base64 = Base64.getEncoder().encodeToString(userAddr.toByteArray())
            val uuid = Base64.getEncoder().encodeToString(sender.id.toString().toByteArray())
            val UUID_BIG = uuid.toUpperCase()
            val UUID_KEY_bebin = "${UUID_BIG.substring(0,4)}-${UUID_BIG.substring(4,8)}-${UUID_BIG.substring(8,12)}"
            val UUID_KEY = "${UUID_KEY_bebin}-${md5(UUID_KEY_bebin).substring(0,4)}"
            println(UUID_KEY)
            subject.sendMessage(
                """${senderName} 
                    |电子签名：$base64
                    |全局识别码: $UUID_KEY
                """.trimMargin()
            )
        }
    }
}

fun md5(input: String): String {
    val digest = MessageDigest.getInstance("MD5")
    val result = digest.digest(input.toByteArray())
    return toHex(result)
}

fun sha256(input: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val result = digest.digest(input.toByteArray())
    return toHex(result)
}

fun toHex(byteArray: ByteArray): String {
    val result = with(StringBuilder()) {
        byteArray.forEach {
            val value = it
            val hex = value.toInt() and (0xFF)
            val hexStr = Integer.toHexString(hex)
            if (hexStr.length == 1) {
                append("0").append(hexStr)
            } else {
                append(hexStr)
            }
        }
        this.toString()
    }
    return result
}