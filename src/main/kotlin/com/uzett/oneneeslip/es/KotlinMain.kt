package com.uzett.oneneeslip.es

import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol.ANDROID_PAD
import com.uzett.oneneeslip.es.plugins.*
import net.mamoe.mirai.alsoLogin

suspend fun main() {
    val bot = BotFactory.newBot(
        BotConfig.qq,
        BotConfig.password
    ) {
        fileBasedDeviceInfo()
        protocol = ANDROID_PAD
    }.alsoLogin()

    bot.apply {
        help()
        soothsaying()
        createUser()
        addressGen()
    }
    bot.join()
}