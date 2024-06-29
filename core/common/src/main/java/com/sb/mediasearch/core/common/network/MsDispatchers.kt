package com.sb.mediasearch.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val msDispatcher: MsDispatchers)

enum class MsDispatchers {
    Default,
    IO,
}