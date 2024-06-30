plugins {
    alias(libs.plugins.ms.android.library)
    alias(libs.plugins.ms.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.sb.mediasearch.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)
    api(projects.core.network)

    implementation(libs.sandwich)
    implementation(libs.kotlinx.serialization.json)

    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)
}