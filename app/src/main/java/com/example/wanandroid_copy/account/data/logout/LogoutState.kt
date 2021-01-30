package com.example.wanandroid_copy.account.data.logout

import android.content.Context
import com.example.a_common.state.UserState
import com.example.a_common.state.collect.CollectListener
import com.example.wanandroid_copy.account.view.LoginActivity

class LogoutState : UserState {

    override fun goCollectActivity(context: Context) {
        goLoginActivity(context)
    }

    override fun login(context: Context) {
        goLoginActivity(context)
    }

    override fun collect(context: Context, position: Int, listener: CollectListener) {
        goLoginActivity(context)
    }

    private fun goLoginActivity(context: Context?) {
        context?.startActivity<LoginActivity>()
    }


}