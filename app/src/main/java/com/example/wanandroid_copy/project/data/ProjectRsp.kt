package com.example.wanandroid_copy.project.data

data class ProjectRsp (
    val curPage : Int,
    val datas : List<Project>,
    val offset : Int,
    val over : Boolean,
    val pageCount : Int,
    val size : Int,
    val total : Int
)