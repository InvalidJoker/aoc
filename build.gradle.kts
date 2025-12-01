plugins {
    kotlin("jvm") version "2.2.20"
}

group = "de.joker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.mordant:mordant:3.0.2")
    implementation("com.github.ajalt.mordant:mordant-coroutines:3.0.2")
}

kotlin {
    jvmToolchain(21)
}