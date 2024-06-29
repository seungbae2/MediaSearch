package com.sb.mediasearch.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sb.mediasearch.core.network.KakaoNetworkDataSource
import com.sb.mediasearch.core.network.model.ImageSearchResponse
import com.sb.mediasearch.core.network.model.VideoSearchResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface KakaoNetworkApi {
    @GET("vclip")
    suspend fun searchVideoContents(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ApiResponse<VideoSearchResponse>

    @GET("image")
    suspend fun searchImageContents(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ApiResponse<ImageSearchResponse>
}


@Singleton
internal class KakaoNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : KakaoNetworkDataSource {

    private val network = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/v2/search/")
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
        .create(KakaoNetworkApi::class.java)

    override suspend fun searchVideoContents(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): ApiResponse<VideoSearchResponse> = network.searchVideoContents(query, sort, page, size)

    override suspend fun searchImageContents(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): ApiResponse<ImageSearchResponse> = network.searchImageContents(query, sort, page, size)
}