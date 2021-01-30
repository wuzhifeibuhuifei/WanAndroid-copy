package com.example.wanandroid_copy.account.data

import com.example.a_common.utils.Preference
import com.kkaka.common.constant.Constant

class UserContext private constructor() {

    // 代理
    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

//    var mstate:UserState =if(isLogin)LoginState() else LogoutState()

    companion object {
        val instance by lazy {
            UserContext()
        }
    }

    /**
     * 登出成功
     */
    fun logoutSuccess() {
        isLogin = false
        Preference.clear()
    }
}