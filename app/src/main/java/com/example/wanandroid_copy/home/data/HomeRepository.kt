package com.example.wanandroid_copy.home.data

import androidx.lifecycle.MutableLiveData
import com.example.a_common.common.State
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.api.BaseObserver
import com.example.wanandroid_copy.common.article.data.ArticleRepository
import com.kkaka.common.ext.execute

// 首页的数据获取
class HomeRepository(loadState: MutableLiveData<State>) : ArticleRepository(loadState) {

    fun getArticle(page: Int, liveData: MutableLiveData<BaseResponse<HomeArticleRsp>>) {
        apiService.getHomeArticle(page).execute(BaseObserver(liveData, loadState, this))
    }

    fun getBanner(liveData: MutableLiveData<BaseResponse<List<BannerRsp>>>) {
        apiService.getBanner().execute(BaseObserver(liveData, loadState, this))
    }

}