package com.sb.mediasearch.core.network.di

import com.sb.mediasearch.core.network.KakaoNetworkDataSource
import com.sb.mediasearch.core.network.retrofit.KakaoNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface ApiModule {
    @Binds
    fun bindsKakaoNetworkDataSource(impl: KakaoNetwork): KakaoNetworkDataSource
}