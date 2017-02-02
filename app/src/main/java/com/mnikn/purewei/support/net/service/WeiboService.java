package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.model.Emotions;
import com.mnikn.purewei.model.Favorite;
import com.mnikn.purewei.model.Status;
import com.mnikn.purewei.model.Timeline;

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
    @GET("suggestions/favorites/hot.json")
    Observable<List<Status>> getHotWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("statuses/home_timeline.json")
    Observable<Timeline> getHomeWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("favorites.json")
    Observable<Favorite> getFavoriteWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);


    @GET("statuses/user_timeline.json")
    Observable<Timeline> getUserWeibo(
            @Query("access_token") String token,
            @Query("page") int page,
            @Query("count") int count);

    @GET("emotions.json")
    Observable<List<Emotions>> getEmotions(
            @Field("access_token") String token);

    @POST("statuses/update.json")
    @FormUrlEncoded
    Observable<Object> createWeibo(
            @Field("access_token") String token,
            @Field("status") String content);

    @POST("statuses/destroy.json")
    @FormUrlEncoded
    Observable<Object> destoryWeibo(
            @Field("access_token") String token,
            @Field("id") long id);


}
