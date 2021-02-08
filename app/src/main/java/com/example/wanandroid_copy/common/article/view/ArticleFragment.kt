package com.example.wanandroid_copy.common.article.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.a_common.base.LifecycleFragment
import com.example.a_common.state.collect.CollectListener
import com.example.a_common.state.collect.CollectRefreshListener
import com.example.a_common.state.collect.CollectState
import com.example.a_common.state.login.LoginSucListener
import com.example.a_common.state.login.LoginSucState
import com.example.wanandroid_copy.MainActivity
import com.example.wanandroid_copy.R
import com.example.wanandroid_copy.account.data.UserContext
import com.example.wanandroid_copy.common.adapter.ArticleAdapter
import com.example.wanandroid_copy.common.article.data.Article
import com.example.wanandroid_copy.common.article.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*

abstract class ArticleFragment<T : ArticleViewModel<*>> : LifecycleFragment<T>(), CollectListener,
    LoginSucListener,
    CollectRefreshListener {

    private var current = 0
    private var collectState = false
    private lateinit var mActivity: Activity

    public lateinit var mArticleAdapter: ArticleAdapter

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as Activity
    }

    override fun initView() {
        super.initView()
        initRefresh()
        initRecycleView()
    }

    private fun initRecycleView() {
        var mRvArticle: RecyclerView = mActivity.findViewById(R.id.mRvArticle)
        mRvArticle.layoutManager = LinearLayoutManager(activity)
        mArticleAdapter = ArticleAdapter(R.layout.item_article, null)
        mRvArticle.adapter = mArticleAdapter

        //加载更多
        mArticleAdapter.setEnableLoadMore(true)
        mArticleAdapter.setOnLoadMoreListener({ onLoadMore() }, mRvArticle)

        mArticleAdapter.setOnItemClickListener { adapter, view, position ->

            val item = mArticleAdapter.getItem(position)
            item?.let {
                startActivity<WebActivity>("url" to it.link, "title" to it.title)
            }
        }

        mArticleAdapter.setOnItemChildClickListener { adapter, view, position ->
            run {
                if (view.id == R.id.mIvCollect) {
                    UserContext.instance.collect(activity, position, this)
                }
            }
        }

        mRvArticle.addOnScrollListener(object : HideScrollListener() {
            override fun onHide() {
                if (activity is MainActivity) {
                    (mActivity as MainActivity).onHide()
                }
            }

            override fun onShow() {
                if (activity is MainActivity) {
                    (mActivity as MainActivity).onShow()
                }
            }

        })

        LoginSucState.addListener(this)
        CollectState.addListener(this)
    }


    abstract fun onRefreshData()

    abstract fun onLoadMore()


    private fun initRefresh() {
        (msrlRefresh as SwipeRefreshLayout).setColorSchemeResources(R.color.colorPrimaryDark)
        (msrlRefresh as SwipeRefreshLayout).setOnRefreshListener {
            onRefreshData()
        }
    }

    override fun dataObserver() {
        mViewModel.mCollectData.observe(this, Observer { response ->
            val item = mArticleAdapter.getItem(current)
            item?.let {
                it.collect = !collectState
                mArticleAdapter.refreshNotifyItemChanged(current)
            }
        })
    }

    fun addData(datas: List<Article>) {
        if (datas.isEmpty()) {
            mArticleAdapter.loadMoreEnd()
            return
        }

        //下拉刷新 注意完成加载更多(存在加载更多时刷新的情况)
        if ((msrlRefresh as SwipeRefreshLayout).isRefreshing) {
            (msrlRefresh as SwipeRefreshLayout).isRefreshing = false
            mArticleAdapter.setNewData(datas)
            mArticleAdapter.loadMoreComplete()
            return
        }

        //加载更多
        mArticleAdapter.addData(datas)
        mArticleAdapter.loadMoreComplete()
    }

    override fun collect(position: Int) {
        val item = mArticleAdapter.getItem(position)
        item?.let {
            current = position
            collectState = it.collect
            if (it.collect) mViewModel.unCollect(it.id) else mViewModel.collect(it.id)
        }
    }

    override fun loginSuccess(username: String, collectIds: List<Int>?) {
        //更新收藏 如果collectIds存在不存在都要做

        collectIds?.let {
            it.forEach { id ->
                mArticleAdapter.data.forEach {
                    if (it.id == id) {
                        it.collect = true
                    }
                }
            }
        } ?: let {
            mArticleAdapter.data.forEach {
                if (it.collect) {
                    it.collect = false
                }
            }
        }
        mArticleAdapter.notifyDataSetChanged()
    }

    override fun onCollectRefresh(id: Int, originId: Int) {
        ((msrlRefresh as SwipeRefreshLayout)).isRefreshing = true
        reLoad()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 需要去缓存里面删除监听器
        LoginSucState.removeListener(this)
        CollectState.removeListener(this)
    }
}