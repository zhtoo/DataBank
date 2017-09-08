package com.hs.mydatabinding.retrofit.user.Service;

import android.database.Observable;

import com.hs.mydatabinding.retrofit.entity.HttpResult;
import com.hs.mydatabinding.retrofit.user.bean.testBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者：zhanghaitao on 2017/8/29 13:54
 * 邮箱：820159571@qq.com
 * @des 联网接口示例
 */

public interface TestService {


    /**
     * get没有测试
     * @param apikey
     * @param phone
     * @return
     */
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<testBean> getResult(@Header("apikey") String apikey, @Query("phone") String phone);

    /**
     * Post测试
     * @return
     */
    @POST("app/account/basic.html")
    Call<testBean> postResult();

    @POST("app/account/basic.html")
    Call<ResponseBody> postResult1();

    @FormUrlEncoded
    @POST("app/upload/pictureList.html")
    Call<HttpResult> postResult2(@FieldMap Map<String,String> params);

    /**
     * 文件上传
       图片上传的路径（图片一般存放在静态服务器，所以地址和后台主机地址不同）
     * @param params
     * @return
     */
    @Multipart
    @POST("app/upload/uploadPic.html")
    Call<HttpResult> upload(@PartMap Map<String, RequestBody> params);

    @Multipart
    @POST
    Call<HttpResult> toImage(@Url String url, @PartMap Map<String, RequestBody> params);

    /**
     * 下载图片
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadPicture(@Url String fileUrl);

    /**
     * 图片断点下载
     * @param range 下载范围
     * @param url   下载地址
     * @return
     */
    @GET
    @Streaming
    Observable<Response<ResponseBody>> download(@Header("Range") String range, @Url String url);


}
