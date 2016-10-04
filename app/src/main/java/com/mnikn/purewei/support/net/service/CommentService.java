package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.support.bean.CommentBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface CommentService {
    @GET("comments/show.json")
    Observable<CommentBean> request(
            @Query("page") int page,
            @Query("access_token") String token,
            @Query("id") long id
    );
}
