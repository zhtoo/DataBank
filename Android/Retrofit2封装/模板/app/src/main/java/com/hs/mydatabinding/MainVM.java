package com.hs.mydatabinding;

import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hs.mydatabinding.databinding.ActivityMainBinding;
import com.hs.mydatabinding.retrofit.HSClient;
import com.hs.mydatabinding.retrofit.RequestCallBack;
import com.hs.mydatabinding.retrofit.entity.HttpResult;
import com.hs.mydatabinding.retrofit.user.Service.TestService;
import com.hs.mydatabinding.retrofit.auxiliary.LogWrap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：zhanghaitao on 2017/8/28 14:40
 * 邮箱：820159571@qq.com
 */

public class MainVM {

    private ActivityMainBinding binding;

    public MainVM() {
    }

    public MainVM(ActivityMainBinding binding) {
        this.binding = binding;
        binding.mText.setText("Hello World!");
    }


    private String text = "测试" + "123456";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    /**
     * GET请求测试
     * （待测试）
     *
     * @param view
     */
    public void testGet(View view) {
        Toast.makeText(MyApplication.getContext(), "testGet", Toast.LENGTH_SHORT).show();
    }

    /**
     * POST请求测试
     *
     * @param view
     */
    public void testPoast(View view) {
       /*
       //普通的bean类不需要关心JSON数据最外层的数据
       Call<testBean> call = HSClient.getService(TestService.class).postResult();
        call.enqueue(new RequestCallBack<testBean>() {
            @Override
            public void onSuccess(Call<testBean> call, Response<testBean> response) {
                testBean testBean = response.body();
                int findHomeVisitingCount = testBean.getFindHomeVisitingCount();
                int firstVerifyCount = testBean.getFirstVerifyCount();
                int replenishCount = testBean.getReplenishCount();
            }
        });*/

       /*
       //直接调用Retrofit的响应类（操作复杂，不建议使用）
       Call<ResponseBody> call = HSClient.getService(TestService.class).postResult1();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    BufferedSource source = response.body().source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset charset     = Charset.forName("UTF-8");
                    LogWrap.e("test", buffer.clone().readString(charset));
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });*/
        //使用最外层数据的Json解析bean类
        Map<String, String> map = new HashMap<>();
        map.put("cusId", "2");
        map.put("type", "1");
        Call<HttpResult> call = HSClient.getService(TestService.class).postResult2(map);
        call.enqueue(new RequestCallBack<HttpResult>() {
            @Override
            public void onSuccess(Call<HttpResult> call, Response<HttpResult> response) {
                response.body().toString();
                LogWrap.e("test", response.body().toString());
            }
        });

        Toast.makeText(MyApplication.getContext(), "testPoast", Toast.LENGTH_SHORT).show();
    }

    /**
     * 文件上传测试
     *
     * @param view
     */
    public void testUpload(View view) {
        //文件路径
        String path = "/storage/sdcard/Download/test01.jpg";
        //创建文件
        File file = new File(path);
        //构建requestbody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //将resquestbody封装为MultipartBody.Part对象（这一步是用作单一图片上传）
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        //上传类型
        RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "borrow");
        //客户ID
        String cusId = "2";
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), cusId);
        //将参数和File放到集合中
        Map<String, RequestBody> map = new HashMap<>();
        map.put("uploads\"; filename=\"image.png\"", requestFile);
        map.put("nid", type);
        map.put("cusId", id);
        Call<HttpResult> call = HSClient.getService(TestService.class).upload(map);
        call.enqueue(new RequestCallBack<HttpResult>() {
            @Override
            public void onSuccess(Call<HttpResult> call, Response<HttpResult> response) {
                LogWrap.e("test", "上传成功");
            }
        });

        Toast.makeText(MyApplication.getContext(), "testUpload", Toast.LENGTH_SHORT).show();
    }

    /**
     * 文件下载测试
     *
     * @param view
     */
    public void testDownload(View view) {
        //随便一个下载地址
        String url = "http://img1.3lian.com/2015/a1/117/d/136.jpg";
        //获取下载服务
        Call<ResponseBody> call = HSClient.getService(TestService.class).downloadPicture(url);
        //提交申请
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //输入流
                InputStream inputStream = null;
                //输出流
                FileOutputStream outputStream = null;
                try {
                    //将响应体的内容转换为字节流
                    inputStream = response.body().byteStream();
                    //创建文件
                    //获取外部存储卡的路径
                    File filesDir = Environment.getExternalStorageDirectory();
                    //创建File
                    File file = new File(Environment.getExternalStorageDirectory(),
                            SystemClock.currentThreadTimeMillis() + ".png");
                    //如果文件不存在
                    if (!file.exists()) file.createNewFile();//创建新的文件
                    //创建新的输出流
                    outputStream = new FileOutputStream(file);
                    //设定每次读取的大小（缓冲区的大小）
                    byte[] fileReader = new byte[1204 * 4];
                    //获取文件的大小
                    long fileSize = response.body().contentLength();
                    //记录已经下载的图片的大小
                    long fileSizeDownloaded = 0;

                    while (true) {
                        //读文件
                        int read = inputStream.read(fileReader);
                        //如果文件读取完成，就返回
                        //当inputStream.read(读取大小)
                        if (read == -1) {
                            break;
                        }
                        //写文件
                        outputStream.write(fileReader, 0, read);
                        //文件大小更新
                        fileSizeDownloaded += read;
                        Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                    }
                    //outputStream.flush();
                } catch (Exception e) {
                    //打印异常
                    e.printStackTrace();
                } finally {
                    //关流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        Toast.makeText(MyApplication.getContext(), "testDownload", Toast.LENGTH_SHORT).show();
    }


}
