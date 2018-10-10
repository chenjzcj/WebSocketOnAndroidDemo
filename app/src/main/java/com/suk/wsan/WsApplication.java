package com.suk.wsan;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.suk.wsan.websocket.WsManager;

import java.util.logging.Logger;

/**
 * Created by Felix.Zhong on 2018/10/9 18:19
 */
public class WsApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initAppStatusListener();
        LogUtils.getConfig().setGlobalTag("Felix_WS");
    }

    private void initAppStatusListener() {
        ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                LogUtils.i("应用回到前台调用重连方法");
                WsManager.getInstance().reconnect();
            }

            @Override
            public void onBecameBackground() {

            }
        });
    }

    public static Context getContext() {
        return context;
    }
}
