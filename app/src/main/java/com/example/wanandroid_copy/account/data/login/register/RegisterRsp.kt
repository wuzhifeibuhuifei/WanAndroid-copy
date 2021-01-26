package com.example.wanandroid_copy.account.data.login.register

data class RegisterRsp(
    var username: String,
    var id: Int,
    var icon: String,
    var type: Int,
    var collectIds: List<Int>
)