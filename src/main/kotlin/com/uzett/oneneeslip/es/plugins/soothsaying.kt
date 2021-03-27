package com.uzett.oneneeslip.es.plugins

import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.subscribeGroupMessages
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun Bot.soothsaying() {
    eventChannel.subscribeGroupMessages {
        case ("#算命", trim = true) {
            val formatted = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)
            val randoms = Random(sender.id + formatted.toInt() + 20051024).nextInt(0,100)
            var randomMsg = "无法判断你今天的运势呢"
            when (randoms) {
                0 -> randomMsg = "你没看错，是0！0！！！\n有考虑过抽卡吗？"
                in 1..10 -> randomMsg = "没错，百分制"
                in 11..30 -> randomMsg = "哇呜qwq..."
                in 31..50 -> randomMsg = "还行...吧...?"
                in 51..60 -> randomMsg = "还行吧！"
                in 61..80 -> randomMsg = "不错的一天"
                in 81..90 -> randomMsg = "好评如潮"
                in 91..99 -> randomMsg = "人气爆棚！"
                100 -> randomMsg = "这就是传说中的欧皇了吧！"
            }
            subject.sendMessage("$senderName 今天的仙气值是 $randoms（$randomMsg）")
        }
    }
}