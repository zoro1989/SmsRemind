package com.ccbjb.tollcollector;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private TextView tvReceiveMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvReceiveMsg = (TextView)findViewById(R.id.receiveMsg);
        intent = getIntent();
        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.WRITE_CONTACTS)
                .request();
        initJpush();
    }

    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        System.out.println("获取权限成功");
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
    }

    private void initJpush(){
        JPushInterface.setDebugMode(true);
        Set<String> tags = new HashSet<String>();
        tags.add("user");
        JPushInterface.setTags(this, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if(i==0){
                }
            }
        });
        JPushInterface.init(this);
    }

    @Override
    protected void onResume() {
        intent = getIntent();
        String receiveMsg = intent.getStringExtra("receiveMsg");
        tvReceiveMsg.setText(("".equals(receiveMsg)||receiveMsg == null)?"等待接收中":receiveMsg);
        super.onResume();
    }
}
