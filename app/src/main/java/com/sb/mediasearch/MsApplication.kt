package com.sb.mediasearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class MsApplication : Application() {
    fun provideCacheDir(): File {
        return cacheDir
    }
}