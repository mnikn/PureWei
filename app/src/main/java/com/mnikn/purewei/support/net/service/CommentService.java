package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.support.bean.CommentBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface CommentService {
    @GET("comments/show.json")
    Observable<CommentBean> getComment(
            @Query("page") int page,
            @Query("access_token") String token,
            @Query("id") long id);

    @FormUrlEncoded
    @POST("comments/create.json")
    Observable<Object> createComment(
            @Field("access_token") String token,
            @Field("comment") String content,
            @Field("id") long id);
}
