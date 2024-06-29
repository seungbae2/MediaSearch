import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.ms.android.library)
    alias(libs.plugins.ms.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.mediasearch.core.network"

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", getProperties("API_KEY"))
        }
    }
}

fun getProperties(propertyKey: String): String =
    gradleLocalProperties(rootDir, providers).getProperty(propertyKey)

dependencies {
    api(projects.core.common)

    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.sandwich)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.kotlinx.coroutines.test)
}