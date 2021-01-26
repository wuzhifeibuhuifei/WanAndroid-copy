package com.example.wanandroid_copy.search.data

import com.example.wanandroid_copy.common.article.data.Article

data class SearchResultRsp(
    var curPage: Int,
    var datas: List<Article>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
)