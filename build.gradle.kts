import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    java
    kotlin("jvm") version "1.4.31"
    application
}

group = "com.uzett"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

tasks.withType(KotlinJvmCompile::class.java) {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib"))

    val miraiVersion = "2.4.2"

    api("net.mamoe:mirai-core-api:$miraiVersion")
    runtimeOnly("net.mamoe:mirai-core:$miraiVersion")
}

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/jcenter")
    }
}


application{
    mainClass.set("com.uzett.oneneeslip.es.KotlinMainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.getMainClass()
            )
        )
    }
}