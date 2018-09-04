package com.yin.netty.action;

public class MyFetcher implements Fetcher {

    final Data data;

    public MyFetcher(Data data){
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallBack callBack) {
        try {
            callBack.onData(data);
        }catch (Exception e){
            callBack.onError(e);
        }
    }
}
