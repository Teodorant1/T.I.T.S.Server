package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException, IOException {


        ConcurrentHashMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<String, Session>();
        ErikaHttp ErikaHttp1 = new ErikaHttp();

        System.out.println("THE HTTP IS REAL");





      Mistakefactory oops = new Mistakefactory();
      oops.setListener1(ErikaHttp1.getMyHttpHandler1().getListener1());

      ThreadPoolExecutor ThreadPoolExecutor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
       ThreadPoolExecutor1.submit(() -> {
            oops.run();});


        ErikaHttp1.launch();

    }


    }

