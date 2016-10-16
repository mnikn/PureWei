package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.support.bean.TimelineBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
