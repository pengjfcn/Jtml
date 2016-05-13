package com.github.jtml.network.manager;


import com.github.jtml.App;
import com.github.jtml.bean.Comment;
import com.github.jtml.bean.GoodsDetail;
import com.github.jtml.bean.GoodsList;
import com.github.jtml.bean.IndexImgList;
import com.github.jtml.bean.Notifications;
import com.github.jtml.bean.ShowBill;
import com.github.jtml.network.factory.MyCustomFactory;
import com.github.jtml.network.service.JtmlServer;
import com.github.jtml.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by pengjf on 2016/5/12.
 */
public class MyRetrofitManager {

    public static final String BASE_NIUPAI_URL   = "http://m.86422468.com/";
    public static final String BASE_IMAGE_URL    = "http://m.86422468.com";
    //短缓存有效期为1秒钟
    public static final int    CACHE_STALE_SHORT = 1;
    //长缓存有效期为7天
    public static final int    CACHE_STALE_LONG  = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE   = "only-if-cached, max-stale=" + CACHE_STALE_LONG;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "max-age=0";
    private static OkHttpClient mOkHttpClient;
    private final  JtmlServer   mJtmlService;

    public static MyRetrofitManager builder() {
        return new MyRetrofitManager();
    }

    private MyRetrofitManager() {

        initOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_NIUPAI_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new MyCustomFactory())
                .build();
        mJtmlService = retrofit.create(JtmlServer.class);
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (MyRetrofitManager.class) {
                if (mOkHttpClient == null) {

                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG).removeHeader("Pragma").build();
            }
        }
    };

    public Observable<List<Comment>> getComments() {
        return mJtmlService.getComments();
    }

    public Observable<List<Notifications>> getNotifications() {
        return mJtmlService.getNotifications();
    }

    public Observable<List<ShowBill>> getShareBills(String id, String pageNo, String pageSize) {
        return mJtmlService.getShareBills(id, pageNo, pageNo);
    }

    public Observable<GoodsDetail> getGoodsDetailById(String id) {
        return mJtmlService.getGoodsDetailById(id);
    }

    public Observable<List<IndexImgList>> getIndexImgList() {
        return mJtmlService.getIndexImgList();
    }

    public Observable<ArrayList<GoodsList>> getGoodList(String id, String pageNo, String pageSize) {
        return mJtmlService.getGoodList(id, pageNo, pageSize);
    }

}
