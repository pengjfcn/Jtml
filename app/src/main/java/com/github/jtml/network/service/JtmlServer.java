package com.github.jtml.network.service;

import com.github.jtml.bean.Comment;
import com.github.jtml.bean.GoodsDetail;
import com.github.jtml.bean.GoodsList;
import com.github.jtml.bean.IndexImgList;
import com.github.jtml.bean.Notifications;
import com.github.jtml.bean.ShowBill;
import com.github.jtml.network.manager.MyRetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pengjf on 2016/5/12.
 */
public interface JtmlServer {

    //评论接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("share/indexSharecommentsList.action")
    Observable<List<Comment>> getComments();

    //最新揭晓接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("lottery/lotteryproductutilList.action")
    Observable<List<Notifications>> getNotifications();

    //商品详情接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("products/goodsDescAjax.action")
    Observable<GoodsDetail> getGoodsDetailById(@Query("id") String id);

    //晒单接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("share/ajaxPage.action")
    Observable<List<ShowBill>> getShareBills(@Query("id") String id, @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);

    //主页面轮播图接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("list/indexImgList.action")
    Observable<List<IndexImgList>> getIndexImgList();

    //主页面商品列表接口
    @Headers(MyRetrofitManager.CACHE_CONTROL_AGE + MyRetrofitManager.CACHE_STALE_SHORT)
    @GET("list/goodsList.action")
    Observable<ArrayList<GoodsList>> getGoodList(@Query("id") String id, @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);
}
