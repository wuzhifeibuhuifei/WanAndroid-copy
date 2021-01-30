package com.example.a_common.state

import android.content.Context
import com.example.a_common.state.collect.CollectListener

/**
 * 用户状态的接口
 */
interface UserState {

    /**
     * 收藏状态
     */
    fun collect(context: Context, position: Int, listener: CollectListener);

    fun login(context: Context)

    fun goCollectActivity(context: Context)
}