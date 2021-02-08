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

    // 用CompositeSubscription进行统一管理，便于后续进行数据的切断
    fun subscribe(disposable: Disposable) = mCompositeSubscription.add(disposable)

    fun unSubscribe() = mCompositeSubscription.dispose()
}