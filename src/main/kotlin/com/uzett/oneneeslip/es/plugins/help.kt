package com.uzett.oneneeslip.es.plugins

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeGroupMessages

fun Bot.help() {
    eventChannel.subscribeGroupMessages {
//        Regex("""^-(帮助|help)$""") matching regex@{
//        }
        case ("#help", trim = true, ignoreCase = true) {
            subject.sendMessage(
                """OneNeeslip ES v1.0.0
                    |#help 查看帮助
                    |#算命 呐呐呐
                """.trimMargin()
            )
        }
    }
}