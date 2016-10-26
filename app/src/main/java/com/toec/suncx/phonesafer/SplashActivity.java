package com.toec.suncx.phonesafer;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.toec.suncx.phonesafer.util.StreamUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tv= (TextView) findViewById(R.id.tv_splash_version);
        tv.setText("版本号"+getVersionName());
        updata();
    }

    private void updata() {
        new Thread(){
            public void run(){
                try{
                    URL url=new URL("xxxxx");
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    int responseCode=conn.getResponseCode();
                    if(responseCode==200){
                        //获取服务器返回的流信息
                        InputStream is=conn.getInputStream();
                        //将获取的流转化为字符串
                        String json= StreamUtil.parserStreamUtil(is);
                        //解析json数据
                        JSONObject jsonObject=new JSONObject(json);
                        String code=jsonObject.getString("code");
                        String apkurl=jsonObject.getString("apkurl");
                        String des=jsonObject.getString("des");
                        System.out.println("code:"+code+"apkurl:"+apkurl+"des:"+des);

                    }else{
                        System.out.println("链接失败");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getVersionName(){
        PackageManager pm= getPackageManager();
        try{
            PackageInfo packgeinfo=pm.getPackageInfo(getPackageName(),0);
            String versionName=packgeinfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
