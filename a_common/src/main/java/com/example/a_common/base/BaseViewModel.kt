package com.example.a_common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a_common.common.State
import com.example.a_common.utils.Util

/**
 * 基础的baseViewModel类
 */
open class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application) {

    /**
     * 延迟生成一个mutableLiveData对象
     */
    val loadState by lazy {
        MutableLiveData<State>()
    }

    /**
     * 通过反射repository对象
     */
    val mRepository: T by lazy {
        (Util.getClass<T>(this)).getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance()
    }


    /**
     * 清除CompositeDisposable中的内容
     */
    override fun onCleared() {
        super.onCleared()
        mRepository.unSubscribe()
    }
}