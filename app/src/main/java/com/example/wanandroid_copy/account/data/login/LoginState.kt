package com.example.wanandroid_copy.account.data.login

import android.content.Context
import com.example.a_common.state.UserState
import com.example.a_common.state.collect.CollectListener
import com.example.wanandroid_copy.collect.view.CollectActivity

class LoginState : UserState {

    override fun collect(context: Context, position: Int, listener: CollectListener) {
        listener.collect(position)
    }

    override fun login(context: Context) {
        TODO("Not yet implemented")
    }

    override fun goCollectActivity(context: Context) {
        // 跳转到收藏页面
        context.startActivity<CollectActivity>()
    }


}