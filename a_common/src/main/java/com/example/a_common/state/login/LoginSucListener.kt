package com.example.a_common.state.login

/**
 * author：  HyZhan
 * created： 2018/10/23 17:27
 * desc：    登录成功的回调
 */
interface LoginSucListener {
    fun loginSuccess(username: String, collectIds: List<Int>?)
}