# WebView的简单使用

## 添加权限

```
<uses-permission android:name="android.permission.INTERNET" />
```

## Android代码

```
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by zhanghaitao on 2017/5/1.
 */

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webapp_test);
        Button button = (Button) findViewById(R.id.webview_bt);
        //设置编码格式
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        webView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
        JavaScriptObject javaScriptObject =new JavaScriptObject(this);
        //添加js的接口
        webView.addJavascriptInterface(javaScriptObject,"myObj");

        //载入本地js
       webView.loadUrl("file:///android_asset/test.html");

        //载入网络端js
       // webView.loadUrl("http://10.0.2.2:8080/test.html");

        //点击调用js中方法
        button.setOnClickListener(this);

    }
    /**
    处理本地点击事件
    */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.webview_bt) {
            String str= "我是来自As的参数";
            //调用网页端JS里面的方法
            webView.loadUrl("javascript:funFromjs('"+str+"')");
        }
    }

    /**
     * 对象名称必须一样  即 创建的对象 myObj
     * 和html 调用的对象 myObj 要保持一致
     */
     
    public class JavaScriptObject {
        Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }
    
        //网页端调用
        @JavascriptInterface
        public void funfromAndroid(String name) {
            Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
        }
        
        //网页端调用
        @JavascriptInterface
        public void  funToMain(){
            Intent intent =new Intent(mContxt,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
```

## asset下的html（asset目录在main目录下）


```
<body>
<a>js中调用本地方法</a>
<script>

  function funFromjs(str){
    document.getElementById("helloWeb").innerHTML=str;
  }

  function funfromAndroid(){
      //调用android本地方法
      var string = "我是来自JS的参数";
      myObj.funfromAndroid(string);
  }
   function funtoMain(){
      //调用android本地方法
      myObj.funToMain();
  }

  </script>
<p></p>
<button onclick="funfromAndroid()">我是JS按钮</button>
<button onclick="funtoMain()">去主页</button>

<div id="helloWeb"></div>

</body>
```

# 备注：
其实WebView里面还有还多方法没有提及，以上只是基本使用，能够满足JS和Android的交互。具体详细的使用，请各位自己在下面补充添加。


