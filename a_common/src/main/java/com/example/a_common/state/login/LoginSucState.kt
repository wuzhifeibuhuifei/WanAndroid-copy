package com.example.a_common.state.login

object LoginSucState {

    var listeners = ArrayList<LoginSucListener>()

    fun addListener(listener: LoginSucListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: LoginSucListener) {
        listeners.remove(listener)
    }

    fun notifyLoginState(name: String, collectIds: List<Int>?) {
        for (listener in listeners) {
            listener.loginSuccess(name, collectIds)
        }
    }
}