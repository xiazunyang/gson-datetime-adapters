buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}