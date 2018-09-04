package com.yin.netty.action;


public interface FetcherCallBack {
    void onData(Data data) throws Exception;
    void onError(Throwable cause);
}
