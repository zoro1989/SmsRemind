package com.ccbjb.tollcollector.util.http;

import android.app.Activity;

import com.ccbjb.tollcollector.util.ToastUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by 仇招 on 2017/4/10.
 */
public class NetworkHelper {
    private Activity context;
    public NetworkHelper(Activity context) {
        this.context = context;
    }

    //  获取IP
    public String getLocalIpAddress() {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            for (; en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ToastUtils.show(context,ex.getMessage());
        }

        return null;
    }
    // 获取MAC
    public String getLocalMacAddressFromIp() {
        String mac_s = "";
        try {
            byte[] mac;
            NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName(getLocalIpAddress()));
            mac = ne.getHardwareAddress();
            mac_s = byte2hex(mac);
        } catch (Exception e) {
            ToastUtils.show(context,e.getMessage());
        }

        return mac_s;
    }

    public String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
        }

        return String.valueOf(hs);
    }
}
