plugins {
    alias(libs.plugins.ms.android.library)
    alias(libs.plugins.ms.android.library.compose)
}

android {
    namespace = "com.sb.mediasearch.core.designsystem"
}

dependencies {
    api(libs.androidx.core.ktx)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    implementation(libs.coil.kt.compose)

    debugApi(libs.androidx.compose.ui.tooling)
    testImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
}