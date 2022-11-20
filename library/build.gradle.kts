import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    id("com.github.dcendents.android-maven")
}

group = "com.github.xiazunyang"
version = "1.0.0"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.9.1")
    testImplementation("com.google.code.gson:gson:2.9.1")
}