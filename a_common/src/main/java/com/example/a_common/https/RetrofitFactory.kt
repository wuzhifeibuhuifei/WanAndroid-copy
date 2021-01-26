package com.example.a_common.https

import com.example.a_common.utils.Preference
import com.kkaka.common.constant.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(initOkHttpClient())
        .build()

    /**
     * 生成okhttp对象
     */
    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(LoggingInterceptor())
            .addInterceptor(initCookieIntercept())
            .addInterceptor(initLoginIntercept())
            .addInterceptor(initCommonInterceptor())
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    /**
     * 请求的时候设置cookie
     */
    private fun initLoginIntercept(): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()
            val domain = request.url().host()

            if (domain.isEmpty()) {
                val cookie by Preference(domain, "")
                if (cookie.isNotEmpty()) {
                    builder.addHeader(domain, cookie)
                }
            }
            it.proceed(request)
        }
    }

    private fun initCookieIntercept(): Interceptor {

    }
}