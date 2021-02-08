package com.example.a_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

abstract class BaseFragment : Fragment() {

    lateinit var loadService: LoadService<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(getLayoutId(), null)
        loadService = LoadSir.getDefault().register(inflate) {
            reLoad()
        }
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()

    open fun initData() {
    }

    open fun reLoad() {
        initData()
    }

    // 获取layout的id
    abstract fun getLayoutId(): Int
}