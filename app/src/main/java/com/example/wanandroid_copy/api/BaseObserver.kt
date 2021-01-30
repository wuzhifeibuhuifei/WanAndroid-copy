package com.example.wanandroid_copy.api

import androidx.lifecycle.MutableLiveData
import com.example.a_common.base.BaseRepository
import com.example.a_common.common.State
import com.example.a_common.common.StateType
import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.account.data.UserContext
import com.kkaka.common.constant.Constant
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class BaseObserver<T : BaseResponse<*>>(
    private val liveData: MutableLiveData<T>,
    private val loadState: MutableLiveData<State>,
    private val repository: BaseRepository
) : Observer<T> {
    override fun onComplete() {
        TODO("Not yet implemented")
    }

    override fun onSubscribe(d: Disposable) {
        repository.subscribe(d)
    }

    override fun onNext(response: T) {
        when (response.errorCode) {
            Constant.RESPONSE_SUCCESS -> {
                if (response.data is List<*>) {
                    if ((response.data as List<*>).isEmpty()) {
                        loadState.postValue(State(StateType.EMPTY))
                        return
                    }
                }
                loadState.postValue(State(StateType.SUCCESS))
                liveData.postValue(response)
            }
            Constant.RESPONSE_NOT_LOGIN -> {
                UserContext.instance.logoutSuccess()
                loadState.postValue(State(StateType.ERROR, msg = "登录过期"))
            }
            else -> {
                loadState.postValue(State(StateType.ERROR, msg = response.errorMsg))
            }
        }
    }

    override fun onError(e: Throwable) {
        TODO("Not yet implemented")
    }


}