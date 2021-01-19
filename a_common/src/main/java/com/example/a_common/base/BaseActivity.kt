package com.example.a_common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.kkaka.common.common.AppManager
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    var disposable: Disposable? = null

    val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) { reLoad() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        // 将当前的activity加入appManager中，进行统一管理
        AppManager.instance.addActivity(this)
        initView()
        initData()
    }

    /*初始化数据*/
    abstract fun initData()

    /*初始化页面*/
    abstract fun initView()

    /*获取显示的layout*/
    abstract fun getLayoutId(): Int

    /*不同的网络转态下的回调内容*/
    abstract fun reLoad()


    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        AppManager.instance.removeActivity(this)
    }

    fun setToolBar(toolbar: Toolbar, appName: String) {
        toolbar.title = appName
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            // 设置首页显示drawer的按钮
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }
}