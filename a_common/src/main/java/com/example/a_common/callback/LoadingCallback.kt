package com.example.a_common.callback

import com.kingja.loadsir.callback.Callback
import com.example.a_common.R

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_loading
}