package com.sample.knowmerce.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class KakaoInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header(HEADER_AUTHORIZATION, "KakaoAK 16e7e516049ae6ddca32e7fa4b628a01")
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }
}