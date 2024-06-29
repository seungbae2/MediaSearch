plugins {
    alias(libs.plugins.ms.android.feature)
    alias(libs.plugins.ms.android.library.compose)
    alias(libs.plugins.ms.android.hilt)
}

android {
    namespace = "com.sb.mediasearch.feature.bookmarks"
}

dependencies {
    implementation(projects.core.designsystem)
}