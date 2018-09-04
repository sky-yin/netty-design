package com.yin.netty.action;

public class Worker {

    public void doWorker(){
        Fetcher fetcher = new MyFetcher(new Data(1,1));
        fetcher.fetchData(new FetcherCallBack() {
            @Override
            public void onData(Data data) throws Exception {
                System.out.println("Data receiver: "+ data);
            }

            @Override
            public void onError(Throwable cause) {
                System.out.println("An error accour: "+ cause.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWorker();
    }
}
