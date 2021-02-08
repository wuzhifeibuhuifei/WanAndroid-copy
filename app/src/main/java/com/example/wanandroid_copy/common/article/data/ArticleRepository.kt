package com.example.wanandroid_copy.common.article.data

import androidx.lifecycle.MutableLiveData
import com.example.a_common.base.BaseRepository
import com.example.a_common.common.State
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.api.ApiRepository
import com.example.wanandroid_copy.api.BaseObserver
import com.kkaka.common.ext.execute
import java.text.FieldPosition

open class ArticleRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    // 文章中的收藏和取消收藏功能
    fun collect(position: Int, liveData: MutableLiveData<BaseResponse<Any>>) {
        apiService.collect(position).execute(BaseObserver(liveData, loadState, this))
    }

    fun unCollect(position: Int, liveData: MutableLiveData<BaseResponse<Any>>) {
        apiService.unCollect(position).execute(BaseObserver(liveData, loadState, this))
    }

}