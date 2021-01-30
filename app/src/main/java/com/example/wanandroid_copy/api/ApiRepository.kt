package com.example.wanandroid_copy.api

import com.example.a_common.base.BaseRepository
import com.example.a_common.https.RetrofitFactory

open class ApiRepository : BaseRepository() {

    val apiService: ApiService by lazy {
        RetrofitFactory().retrofit.create(ApiService::class.java)
    }

}