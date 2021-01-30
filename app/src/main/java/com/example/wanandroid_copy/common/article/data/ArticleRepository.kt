package com.example.wanandroid_copy.common.article.data

import androidx.lifecycle.MutableLiveData
import com.example.a_common.base.BaseRepository
import com.example.a_common.common.State
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.api.ApiRepository
import java.text.FieldPosition

class ArticleRepository(val loadState: MutableLiveData<State>): ApiRepository() {

    // 文章中的收藏和取消收藏功能
    fun collect(position: Int, liveData: MutableLiveData<BaseResponse<Any>>) {
        apiService.collect(position).execute(BaseObserver(liveData,loadState,this))
    }

    fun unCollect() {

    }

}