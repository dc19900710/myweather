package com.dc.myweather.app.util;

/**
 * Created by DC on 2015/4/14.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
