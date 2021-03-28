package com.company;

import java.sql.SQLException;
import java.util.ArrayList;

public class Session {
    //this object holds our player objects


    public RNGjesus rnGjesus1 = new RNGjesus();
    private String gameid = "placeholder";
    private String Question = "placeholder";
    public ArrayList<Player> players = new ArrayList<Player>();

    //doomsdayclock is part of my effort of defensive programming
    //in human words it basically checks the all the sessions every 15 minutes, and they get
    //deleted if nobody has been listening to it for 30 minutes or so
    //check the gamelist for more info
    private String doomsdayclock = "no";
    SqlMinion sqlminionSession1 = new SqlMinion();
    public String electorcount = "placeholder";


    private String password;

    public Session() {
    }

    public Session(String gameid) {
        this.gameid = gameid;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public void addplayer(String playername) {
        this.players.add(new Player(playername));
    }


    public String getDoomsdayclock() {
        return doomsdayclock;
    }

    public void setDoomsdayclock(String doomsdayclock) {
        this.doomsdayclock = doomsdayclock;
    }

    public String getElectorcount() {
        return electorcount;
    }

    public void setElectorcount(String electorcount) {
        this.electorcount = electorcount;
    }

    public void createQuestion() throws SQLException {
        this.setQuestion(rnGjesus1.CreateCard());
    }


    //combines the questions of all the players in a single blob,
    // to be delivered to
    //the frontend
    public String getAnswers() {
        StringBuilder endgamepaloki = new StringBuilder(" ");
        if (this.players.size() > 0) {
            for (int i = 0; i < this.players.size(); i++) {
                endgamepaloki.append(" " + this.players.get(i).getPlayername() + "gave this answer" + " " + this.players.get(i).getAnswer() + " ");
            }
        }

        return String.valueOf(endgamepaloki);
    }


    //this is here because I didn't make players object into concurrenthashmap
    public ArrayList<String> adhoccheck() {
        ArrayList<String> playernames = new ArrayList<String>();
        for (int i = 0; i < this.players.size(); i++) {
            playernames.add(players.get(i).getPlayername());
        }
        return playernames;
    }

    //same as getanswers but for scores
    public String getScores() {
        StringBuilder endgamepaloki = new StringBuilder(" ");
        if (this.players.size() > 0) {
            for (int i = 0; i < this.players.size(); i++) {
                endgamepaloki.append(" " + "Judge Status" + "  " + this.getElectorcount() + " " + this.players.get(i).getPlayername() + " "
                        + "has this number of points" + " " + this.players.get(i).getCard());
            }
        }

        return String.valueOf(endgamepaloki);
    }

    //adhoc way to increment score because again
    //players isn't a concurrenthashmap
    public void incrementScore(String Player, String Judge) {
        for (int i = 0; i < this.players.size(); i++) {
            if ((this.players.get(i).getPlayername().equals(Player) && (this.getElectorcount().equals(Judge)))) {
                this.players.get(i).setScore(this.players.get(i).getScore() + 1);
                this.setElectorcount(Player);
            }
        }
    }

    //this isn't super evident here
    //but essentially if the judge tries to cheat and give himself points
    //instead somebody else becomes the judge and he gets no points
    public void d20roll() {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getPlayername().equals(this.electorcount)) {
                System.out.println("derp");
            } else this.setElectorcount(this.players.get(i).getPlayername());
        }
    }

    //answer insertion because I realized too late
    //that players should've been a concurrenthashmap
    public void insAnswer(String player, String answer) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getPlayername().equals(player)) {
                this.players.get(i).setAnswer(answer);
            }
        }
    }
}
