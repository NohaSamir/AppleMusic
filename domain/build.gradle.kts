plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.javax.inject)
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}