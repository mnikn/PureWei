package com.mnikn.purewei.support.net.service;

import com.mnikn.purewei.support.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface UidService {
    @GET("account/get_uid.json")
    Observable<UserBean> request(
            @Query("access_token") String token
    );
}
