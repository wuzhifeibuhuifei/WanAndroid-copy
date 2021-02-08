package com.example.wanandroid_copy.common.article.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.a_common.base.BaseRepository
import com.example.a_common.base.BaseViewModel
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.common.article.data.ArticleRepository

open class ArticleViewModel<T : ArticleRepository>(application: Application) : BaseViewModel<T>(application) {

    var mCollectData: MutableLiveData<BaseResponse<Any>> = MutableLiveData()

    fun collect(id : Int){
        mRespository.collect(id,mCollectData)
    }

    fun unCollect(id: Int){
        mRespository.unCollect(id,mCollectData)
    }

}