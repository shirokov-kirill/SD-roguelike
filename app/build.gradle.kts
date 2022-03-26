import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val zircon_version: String by project

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.ksshi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.hexworks.zircon:zircon.core-jvm:$zircon_version")
    implementation("org.hexworks.zircon:zircon.jvm.swing:$zircon_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}