package com.company;

public class request {

    public request(String controller, String methodname,
                   String gameid, String playername,
                   String payload1, String payload2
            , String playerPassword, String gamepassword) {
        this.controller = controller;
        this.methodname = methodname;

        this.gameid = gameid;
        this.playername = playername;

        this.Payload1 = payload1;
        this.Payload2 = payload2;
        this.playerPassword = playerPassword;
        this.gamepassword = gamepassword;
    }
    String controller;
    String methodname;
    String gameid;


    String playername;

    String expansion;
    // the ones up above are used when creating games
    // expansion is extra special
    String Payload1;
    String Payload2;
    String playerPassword;
    String gamepassword;

    String batchDestination;
    String author;
    String authorPassword;

    public request(){}


    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getPayload1() {
        return Payload1;
    }

    public void setPayload1(String payload1) {
        Payload1 = payload1;
    }

    public String getPayload2() {
        return Payload2;
    }

    public void setPayload2(String payload2) {
        Payload2 = payload2;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }

    public String getGamepassword() {
        return gamepassword;
    }

    public void setGamepassword(String gamepassword) {
        this.gamepassword = gamepassword;
    }

    public String getBatchDestination() {
        return batchDestination;
    }

    public void setBatchDestination(String batchDestination) {
        this.batchDestination = batchDestination;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorPassword() {
        return authorPassword;
    }

    public void setAuthorPassword(String authorPassword) {
        this.authorPassword = authorPassword;
    }





}
