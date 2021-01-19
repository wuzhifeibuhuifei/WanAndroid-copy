package com.example.wanandroid_copy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.a_common.base.BaseActivity
import com.example.a_common.state.login.LoginSucListener
import com.example.a_common.utils.Preference
import com.kkaka.common.constant.Constant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity(), LoginSucListener {


    private var mUserName: String by Preference(Constant.USERNAME_KEY, "未登录")

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initToolbar()
        initDrawerLayout()
        initFabButton()

    }

    /*初始化首页右下角的查询按钮功能*/
    private fun initFabButton() {
        mNavigationBar.setFab(fab)
        fab.setOnClickListener {
            // todo 跳转到查询页面
            // startActivity<SearchActivity>()
        }
    }

    /*初始化drawer*/
    private fun initDrawerLayout() {
        val headerView = navigation.getHeaderView(0)
        headerView.tv_name.text = mUserName
        headerView.iv_logo.setOnClickListener {
            // TODO 跳转到登录的界面，进行登录
        }
        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_collect -> {
                    // todo 跳转到收藏页面
                    // goCollectActivity()
                }
                R.id.nav_menu_logout -> {
                    // todo 跳转到退出登录页面
                    // logout()
                }
                R.id.nav_about -> {
                    // todo 跳转到关于我们页面
                    //goAbout()
                }
            }
            // 关闭drawer
            drawerMain.closeDrawers()
            true
        }
    }

    /*初始化头部*/
    private fun initToolbar() {
        //  设置头部菜单名称
        setToolBar(toolbar, getString(R.string.app_name))
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerMain, R.string.app_name, R.string.app_name)
        // 添加监听器
        drawerMain.addDrawerListener(actionBarDrawerToggle)
        // 同步状态/同步drawer的图标状态
        actionBarDrawerToggle.syncState()
    }

    override fun initData() {
        println("")
    }


    override fun reLoad() {
        TODO("Not yet implemented")
    }

    override fun loginSuccess(username: String, collectIds: List<Int>?) {
        TODO("Not yet implemented")
    }

}