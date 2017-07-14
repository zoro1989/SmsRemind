package com.ccbjb.tollcollector;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;

import com.ccbjb.tollcollector.util.ToastUtils;
import com.ccbjb.tollcollector.util.http.BaseCallback;
import com.ccbjb.tollcollector.util.http.OkHttpHelper;
import com.ccbjb.tollcollector.util.http.SpotsCallBack;
import com.ccbjb.tollcollector.util.http.TKMBaseParseBean;
import com.ccbjb.tollcollector.util.http.TmpCallBack;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SmsReciver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String pushContent = "您在："+ receiveTime+"收到了 号码为:" + msg.getOriginatingAddress()
                        + "的短信   短信内容为:" + msg.getDisplayMessageBody();
                System.out.println(pushContent);

                Map<String, String> map = new HashMap<String, String>();
                // 所辖片区ID
                map.put("pushTitle", "短信通知");
                map.put("pushContent", pushContent);
                map.put("pushTag", "user");
                OkHttpHelper.getInstance().post("http://smsremind.duapp-preview.com/push/send.tkm", map, new BaseCallback<TKMBaseParseBean>() {
                    @Override
                    public void onBeforeRequest(Request request) {

                    }

                    @Override
                    public void onFailure(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Response response) {

                    }

                    @Override
                    public void onSuccess(Response response, TKMBaseParseBean model) {

                    }

                    @Override
                    public void onError(Response response, int code, Exception e) {

                    }
                });

            }
        }
    }

} 