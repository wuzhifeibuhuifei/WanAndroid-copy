package com.example.wanandroid_copy.api

import com.example.a_common.https.BaseResponse
import com.example.wanandroid_copy.account.data.login.LoginRsp
import com.example.wanandroid_copy.account.data.login.register.RegisterRsp
import com.example.wanandroid_copy.collect.data.CollectRsp
import com.example.wanandroid_copy.home.data.BannerRsp
import com.example.wanandroid_copy.home.data.HomeArticleRsp
import com.example.wanandroid_copy.navigation.data.NavigationCategoryRsp
import com.example.wanandroid_copy.project.data.ProjectRsp
import com.example.wanandroid_copy.project.data.ProjectTabRsp
import com.example.wanandroid_copy.search.data.HotSearchRsp
import com.example.wanandroid_copy.search.data.SearchResultRsp
import com.example.wanandroid_copy.system.data.SystemArticleRsp
import com.example.wanandroid_copy.system.data.TopMenuRsp
import com.example.wanandroid_copy.wechat.data.WeChatListRsp
import com.example.wanandroid_copy.wechat.data.WeChatNameRsp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 封装api接口地址
 * 通过Retrofit包
 */
interface ApiService {

    /**
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<BaseResponse<HomeArticleRsp>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 登录
     */
    @POST("/user/login")
    fun getLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<BaseResponse<LoginRsp>>

    /**
     * 注册
     */
    @POST("/user/register")
    fun getRegister(
        @Query("username") username: String, @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Observable<BaseResponse<RegisterRsp>>

    /**
     * 获取收藏
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectArticle(@Path("page") page: Int): Observable<BaseResponse<CollectRsp>>

    /**
     * 取消收藏页收藏
     */
    @POST("/lg/uncollect/{id}/json")
    fun unMyCollect(
        @Path("id") id: Int,
        @Query("originId") originId: Int
    ): Observable<BaseResponse<Any>>

    /**
     * 获取首页轮播图
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerRsp>>>

    /**
     * 获取微信头
     */
    @GET("/wxarticle/chapters/json")
    fun getWeChat(): Observable<BaseResponse<List<WeChatNameRsp>>>

    /**
     * 获取微信文章列表
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    fun getWeChatList(@Path("id") id: Int, @Path("page") page: Int)
            : Observable<BaseResponse<WeChatListRsp>>

    /**
     * 导航页面数据
     */
    @GET("/navi/json")
    fun getCategory(): Observable<BaseResponse<List<NavigationCategoryRsp>>>

    /**
     * 搜索
     */
    @POST("/article/query/{page}/json")
    fun search(
        @Path("page") page: Int,
        @Query("k") key: String
    ): Observable<BaseResponse<SearchResultRsp>>

    /**
     * 热门搜索
     */
    @GET("/hotkey/json")
    fun getHotKey(): Observable<BaseResponse<List<HotSearchRsp>>>

    /**
     * 体系一级菜单
     */
    @GET("/tree/json")
    fun getTopMenu(): Observable<BaseResponse<List<TopMenuRsp>>>

    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{page}/json")
    fun getSystemArticles(
        @Path("page") page: Int,
        @Query("cid") id: Int
    ): Observable<BaseResponse<SystemArticleRsp>>

    /**
     * 获取项目Tab
     */
    @GET("/project/tree/json")
    fun getProjectTab(): Observable<BaseResponse<List<ProjectTabRsp>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{page}/json")
    fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Observable<BaseResponse<ProjectRsp>>

}