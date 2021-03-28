package com.company;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) throws SQLException {
        gamelist gamelist1 = new gamelist();
        Mistakefactory oops = new Mistakefactory(gamelist1);
        ThreadPoolExecutor ThreadPoolExecutor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
//check Mistakefactory to understand how we basically use objects like nesting dolls
        //to trick java into multithreading how we want it.
        ThreadPoolExecutor1.submit(() -> { gamelist1.run();});
        ThreadPoolExecutor1.submit(() -> { oops.run();});}}
