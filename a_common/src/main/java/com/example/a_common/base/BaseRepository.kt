package com.example.a_common.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 对请求数据进行批量处理
 *
 */
open class BaseRepository {

    /**
     * rxAndroid的内容
     * 后续进行学习
     */
    private val mCompositeSubscription by lazy {
        CompositeDisposable()
    }

    fun subscribe(disposable: Disposable) = mCompositeSubscription.add(disposable)

    fun unSubscribe() = mCompositeSubscription.dispose()
}