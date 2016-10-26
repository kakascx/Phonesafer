package com.toec.suncx.phonesafer;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tv= (TextView) findViewById(R.id.tv_splash_version);
        tv.setText("版本号"+getVersionName());
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
