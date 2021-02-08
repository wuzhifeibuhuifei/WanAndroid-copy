package com.example.wanandroid_copy.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.common.article.viewmodel.ArticleViewModel
import com.example.wanandroid_copy.home.data.BannerRsp
import com.example.wanandroid_copy.home.data.HomeArticleRsp
import com.example.wanandroid_copy.home.data.HomeRepository

class HomeViewModel(application: Application) : ArticleViewModel<HomeRepository>(application) {

    val mHomeArticleData: MutableLiveData<BaseResponse<HomeArticleRsp>> = MutableLiveData()
    val mBannerData: MutableLiveData<BaseResponse<List<BannerRsp>>> = MutableLiveData()

    fun getArticle(page: Int) {
        mRespository.getArticle(page, mHomeArticleData)
    }

    fun getBanner() {
        mRespository.getBanner(mBannerData)
    }

}