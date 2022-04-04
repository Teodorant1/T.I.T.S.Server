package com.company;

import java.util.ArrayList;
//this is the player object
// players have a score and answer associated with them, also a judge status because this game is inspired by cards against humanity

public class Player
{   private String playername;
    private String password;



    private String Answer = "placeholder";
    private int score = 0;


//here we can notice the constructor and the various setters and getters
    public Player() {}

    public Player (String playername , String password )
    {this.playername=playername;
     this.password = password;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


//this is a useful shorthand for our session (the object that holds our players)
    public String getCard()
    { return getPlayername()+ " : " +getScore();}

}
