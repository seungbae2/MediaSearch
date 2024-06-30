plugins {
    alias(libs.plugins.ms.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sb.mediasearch.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
}