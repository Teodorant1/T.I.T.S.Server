package com.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

//tldr this basically checks the number of rows in the questions
//and does some rng to generate random question combinations and
//has some defensive programming to not generate errors if you exhaust all the question
//combinations

public class RNGRobot {

    static SqlRobot sqlrobot1 = new SqlRobot();
    static ArrayList<Integer> qnumbers = new ArrayList();
    static ArrayList<Integer> snumbers = new ArrayList();
    int gamesize;

    public int getGamesize() {
        return gamesize;
    }

    public void setGamesize(int gamesize) {
        this.gamesize = gamesize;
    }

  //  public void printArray (ArrayList<Integer> stuff)
 //   { stuff.forEach((Integer)->{ System.out.println(stuff.get(Integer)); });}

    public RNGRobot() {
    }



    public void customPreparation ( String expansionname ) throws SQLException
    {   ArrayList<Integer> qlength = sqlrobot1.getUniqueCreatorSizeQQQ(expansionname);
        ArrayList<Integer> slength = sqlrobot1.getUniqueCreatorSizeSSS(expansionname);
        if (qlength.size() >= slength.size()) {
            setGamesize(slength.size());
        } else {
            setGamesize(qlength.size());
        }

        System.out.println("Custom");

        if (qnumbers.size()<1 && snumbers.size()<1)
        {for (int i = 1; i < getGamesize(); i++) {
            qnumbers.add(qlength.get(i));
            snumbers.add(slength.get(i));
        }}



        Collections.shuffle(qnumbers);
        Collections.shuffle(snumbers); }

    public void preparedecks() throws SQLException {

        int qlength = sqlrobot1.questSIZE();
        int slength = sqlrobot1.sitsSIZE();
        if (qlength >= slength) {
            setGamesize(slength);
        } else {
            setGamesize(qlength);
        }
        System.out.println("All");
        if (qnumbers.size()<1 && snumbers.size()<1)
        {for (int i = 1; i < getGamesize(); i++) {
            qnumbers.add(i);
            snumbers.add(i);}}

        Collections.shuffle(qnumbers);
        Collections.shuffle(snumbers);
     // System.out.println(qnumbers.size());
        // printArray(qnumbers);


    }

    public String CreateCard() throws SQLException {

        if ((qnumbers.size() > 0) && (snumbers.size() > 0)) {
            ArrayList<String> payload = new ArrayList();

            String s = (sqlrobot1.pull_SITUATION(snumbers.get(0)));
            String q = (sqlrobot1.pull_QUESTION(qnumbers.get(0)));
            payload.add(s + " , " + q);
            snumbers.remove(0);
            qnumbers.remove(0);
            return (payload.get(payload.size() - 1));
        }
        return " Ya'll have exhausted all the questions";
    }
}








