package com.example.wanandroid_copy.wechat.data

import com.example.wanandroid_copy.common.article.data.Article


data class WeChatListRsp(
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)