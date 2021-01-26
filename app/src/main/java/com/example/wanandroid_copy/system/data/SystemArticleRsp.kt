package com.example.wanandroid_copy.system.data

import com.example.wanandroid_copy.common.article.data.Article

data class SystemArticleRsp(
    var curPage: Int,
    var datas: List<Article>,
    var pageCount: Int,
    var total: Int
)