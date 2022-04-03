import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    implementation("org.hexworks.zircon:zircon.core-jvm:2020.2.0-RELEASE")
    implementation("org.hexworks.zircon:zircon.jvm.swing:2020.2.0-RELEASE")
    implementation("org.hexworks.amethyst:amethyst.core-jvm:2020.1.1-RELEASE")
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