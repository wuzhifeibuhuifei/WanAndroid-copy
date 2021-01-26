package com.example.wanandroid_copy.collect.data

import com.example.wanandroid_copy.common.article.data.Article

data class CollectRsp(
    var curPage: Int,
    var datas: List<Article>,
    var total: Int
)