package com.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

//tldr this basically checks the number of rows in the questions
//and does some rng to generate random question combinations and
//has some defensive programming to not generate errors if you exhaust all the question
//combinations

public class RNGjesus {

    static   SqlMinion Sqlminion1 = new SqlMinion();
    static   ArrayList <Integer> qnumbers = new ArrayList();
    static   ArrayList <Integer> snumbers = new ArrayList();
    static   ArrayList <String> payload = new ArrayList();
    int gamesize;
    public int getGamesize() {
        return gamesize;
    }

    public void setGamesize(int gamesize) {
        this.gamesize = gamesize;
    }


    public RNGjesus(){}

    public void preparedecks () throws SQLException {

        int qlength = Sqlminion1.questSIZE();
        int slength = Sqlminion1.sitsSIZE();
        if (qlength >= slength) {setGamesize(slength); }
        else {setGamesize(qlength);}
        for (int i = 1; i < gamesize; i++)
        {qnumbers.add(i); snumbers.add(i);}

        Collections.shuffle(qnumbers);
        Collections.shuffle(snumbers);}

    public String CreateCard () throws SQLException{

        if( (qnumbers.size() > 0) && (snumbers.size() > 0))
        {String s = (Sqlminion1.pull_SITUATION(snumbers.get(0)));
        String q = (Sqlminion1.pull_QUESTION(qnumbers.get(0)));
        payload.add(s + " , " + q);
        snumbers.remove(0);
        qnumbers.remove(0);
        return (payload.get(payload.size() - 1 ) );}
        return " Ya'll have exhausted all the questions, maybe stop playing the game";}
    }

    //   public String createCardText (ArrayList <String>Shuffleboy)     {return Shuffleboy }






