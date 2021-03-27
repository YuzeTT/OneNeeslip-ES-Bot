package com.uzett.oneneeslip.es.plugins

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeGroupMessages

fun Bot.createUser() {
    eventChannel.subscribeGroupMessages {
//        "qwq" containsReply {
//            "OK"
//        }
        // 当消息前缀为 "我是" 时
        startsWith("cu", removePrefix = true) {
            // it: 删除了消息前缀 "我是" 后的消息
            // 如一条消息为 "我是张三", 则此时的 it 为 "张三".
            if (it.length > 1) {
                val args = it.split(" ").toTypedArray()
                println(args.toString())
                subject.sendMessage("用户2: ${args[1]}")
            }
            subject.sendMessage("用户: $it")
        }

    }
}