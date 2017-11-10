package com.homa.catcartoon.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
    /**
     * 搜索
     *
     * @return
     */
    @GET("search/index/nickname/{url}")
    Observable<String> getSearch(@Path("url") String url);
    /**
     * 漫画详情
     *
     * @return
     */
    @GET("comic{num}")
    Observable<String> getInfo(@Path(value = "num",encoded = false) String num);

    /**
     * 连载
     *
     * @return
     */
    @GET("lianzai/{num}")
    Observable<String> getLianzai(@Path("num") String num);

    /**
     * 完结
     *
     * @return
     */
    @GET("wanjie/{num}")
    Observable<String> getWanjie(@Path("num") String num);
    /**
     * 搞笑
     *
     * @return
     */
    @GET("list/list_7_{num}.htm")
    Observable<String> getGaoxiao(@Path("num") String num);
    /**
     * 热血
     *
     * @return
     */
    @GET("list/list_5_{num}.htm")
    Observable<String> getRexue(@Path("num") String num);
    /**
     * 悬疑
     *
     * @return
     */
    @GET("/list/list_10_{num}.htm")
    Observable<String> getxuanyi(@Path("num") String num);
    /**
     * 格斗
     *
     * @return
     */
    @GET("list/list_2_{num}.htm")
    Observable<String> getGedou(@Path("num") String num);
    /**
     * 恐怖
     *
     * @return
     */
    @GET("list/list_9_{num}.htm")
    Observable<String> getKongbu(@Path("num") String num);
    /**
     * 科幻
     *
     * @return
     */
    @GET("list/list_11_{num}.htm")
    Observable<String> getKehuan(@Path("num") String num);
    /**
     * 魔幻
     *
     * @return
     */
    @GET("list/list_1_{num}.htm")
    Observable<String> getMohuan(@Path("num") String num);
    /**
     * 励志
     *
     * @return
     */
    @GET("list/list_8_{num}.htm")
    Observable<String> getLizhi(@Path("num") String num);
    /**
     * 校园
     *
     * @return
     */
    @GET("list/list_20_{num}.htm")
    Observable<String> getXiaoyuan(@Path("num") String num);
    /**
     * 爱情
     *
     * @return
     */
    @GET("list/list_4_{num}.htm")
    Observable<String> getAiqing(@Path("num") String num);
    /**
     * 武侠
     *
     * @return
     */
    @GET("list/list_15_{num}.htm")
    Observable<String> getWuxia(@Path("num") String num);
    /**
     * 日漫
     *
     * @return
     */
    @GET("list/comic_2_{num}.htm")
    Observable<String> getRiman(@Path("num") String num);
    /**
     * 港漫
     *
     * @return
     */
    @GET("list/comic_1_{num}.htm")
    Observable<String> getGangman(@Path("num") String num);
    /**
     * 国漫
     *
     * @return
     */
    @GET("list/comic_6_{num}.htm")
    Observable<String> getGuoman(@Path("num") String num);
    /**
     * 美漫
     *
     * @return
     */
    @GET("list/comic_3_{num}.htm")
    Observable<String> getMeiman(@Path("num") String num);


}
