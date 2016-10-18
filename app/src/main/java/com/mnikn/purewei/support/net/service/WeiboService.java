package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.support.bean.EmotionsBean;
import com.mnikn.purewei.support.bean.TimelineBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface WeiboService {
    @GET("statuses/public_timeline.json")
    Observable<TimelineBean> getHotWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("statuses/home_timeline.json")
    Observable<TimelineBean> getHomeWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("statuses/user_timeline.json")
    Observable<TimelineBean> getUserWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("emotions.json")
    Observable<List<EmotionsBean>> getEmotions(
            @Field("access_token") String token);

    @POST("statuses/update.json")
    @FormUrlEncoded
    Observable<Object> postWeibo(
            @Field("access_token") String token,
            @Field("status") String content);

    @POST("statuses/destroy.json")
    @FormUrlEncoded
    Observable<Object> destoryWeibo(
            @Field("access_token") String token,
            @Field("id") long id);


}
