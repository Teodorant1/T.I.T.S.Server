package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ErikaHttp {

    HttpServer server = HttpServer.create(new InetSocketAddress(8001), 20000);
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    MyHttpHandler myHttpHandler1 = new MyHttpHandler();

    public ErikaHttp() throws IOException {}


    public MyHttpHandler getMyHttpHandler1() {
        return myHttpHandler1;
    }

    public void setMyHttpHandler1(MyHttpHandler myHttpHandler1) {
        this.myHttpHandler1 = myHttpHandler1;
    }

    public void launch()
    {
        System.out.println("AND HER NAME IS ERIKA");

        server.createContext("/", myHttpHandler1);

        server.setExecutor(threadPoolExecutor);

        server.start();

    }

}