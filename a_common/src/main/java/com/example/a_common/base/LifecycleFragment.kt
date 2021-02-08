package com.example.a_common.base

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.a_common.callback.EmptyCallback
import com.example.a_common.callback.ErrorCallback
import com.example.a_common.callback.LoadingCallback
import com.example.a_common.common.State
import com.example.a_common.common.StateType
import com.example.a_common.utils.Util
import com.kingja.loadsir.callback.SuccessCallback
import org.jetbrains.anko.toast

abstract class LifecycleFragment<T : BaseViewModel<*>> : BaseFragment() {

    lateinit var mViewModel: T


    private val observer by lazy {
        Observer<State> {
            it?.let {
                when (it.code) {
                    // 通过不同的状态页面显示不同的内容
                    StateType.EMPTY -> showEmpty()
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.msg)
                    StateType.NETWORK_ERROR -> showError("网络异常")
                    StateType.TIP -> showTip(it.msg)
                }
            }
        }
    }

    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun showEmpty() {
        loadService.showCallback(EmptyCallback::class.java)
    }

    open fun showError(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            activity?.toast(msg)
        }
        loadService.showCallback(ErrorCallback::class.java)
    }

    open fun showTip(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            activity?.toast(msg)
        }
        loadService.showCallback(SuccessCallback::class.java)
    }

    override fun initView() {
        showLoading()
        // 实例化ViewModel
        mViewModel = ViewModelProviders.of(this).get(Util.getClass(this))
        // 监控State中code的状态变化
        mViewModel.loadState.observe(this, observer)
        //设置view的observer
        dataObserver()
    }

    abstract fun dataObserver()

    // 显示loading按钮
    private fun showLoading() {
        loadService.showCallback(LoadingCallback::class.java)
    }

    override fun reLoad() {
        showLoading()
        super.reLoad()
    }
}