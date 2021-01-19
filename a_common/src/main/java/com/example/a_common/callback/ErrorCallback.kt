package com.example.a_common.callback

import com.example.a_common.R
import com.kingja.loadsir.callback.Callback

/**
 * @author Laizexin on 2019/12/2
 * @description
 */
class ErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.layout_error
}