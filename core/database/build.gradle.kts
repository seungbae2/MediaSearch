plugins {
    alias(libs.plugins.ms.android.library)
    alias(libs.plugins.ms.android.hilt)
    alias(libs.plugins.ms.android.room)
}

android {
    namespace = "com.sb.mediasearch.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)
}