package com.company;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConcurrentHashMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<String, Session>();

        listener listener1 = new listener(3333 , sessionConcurrentHashMap);
        listener listener2 = new listener(3334 , sessionConcurrentHashMap);
        listener listener3 = new listener(3335 , sessionConcurrentHashMap);
        listener listener4 = new listener(3336 , sessionConcurrentHashMap);
        listener listener5 = new listener(3337 , sessionConcurrentHashMap);
        listener listener6 = new listener(3338 , sessionConcurrentHashMap);
        listener listener7 = new listener(3339 , sessionConcurrentHashMap);


        Mistakefactory oops = new Mistakefactory(listener1);
        ThreadPoolExecutor ThreadPoolExecutor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ThreadPoolExecutor1.submit(() -> { listener1.run();});
        ThreadPoolExecutor1.submit(() -> { listener2.run();});
        ThreadPoolExecutor1.submit(() -> { listener3.run();});
        ThreadPoolExecutor1.submit(() -> { listener4.run();});
        ThreadPoolExecutor1.submit(() -> { listener5.run();});
        ThreadPoolExecutor1.submit(() -> { listener6.run();});
        ThreadPoolExecutor1.submit(() -> { listener7.run();});
        ThreadPoolExecutor1.submit(() -> { oops.run();});}}
