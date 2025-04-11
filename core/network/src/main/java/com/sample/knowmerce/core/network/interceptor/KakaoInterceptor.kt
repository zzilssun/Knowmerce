package com.sample.knowmerce.core.network.interceptor

import com.sample.knowmerce.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KakaoInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header(HEADER_AUTHORIZATION, BuildConfig.KAKAO_AUTH)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }
}