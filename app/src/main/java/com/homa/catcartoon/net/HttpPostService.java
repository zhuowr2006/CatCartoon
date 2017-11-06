package com.homa.catcartoon.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 请求接口类
 */

public interface HttpPostService {

    /**
     * 首页推荐
     *
     * @return
     */
    @GET(".")
    Observable<String> getRecommend();


}
