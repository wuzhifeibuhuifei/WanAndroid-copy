package com.example.wanandroid_copy.common.article.data

/**
 * 文章的数据类
 */
data class Article(
    var id: Int,
    var author: String,
    var chapterName: String?,
    var desc: String,
    var link: String,
    var originId: Int,
    var title: String,
    var collect: Boolean,
    var superChapterName: String?,
    var niceDate: String,
    var niceShareDate: String,
    var shareUser: String,
    var fresh: Boolean
)