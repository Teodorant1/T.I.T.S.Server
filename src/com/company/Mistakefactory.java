package com.company;

public class Mistakefactory implements Runnable {

    gamelist gamelist1;
//this main purpose of this is to basically trick java into effectively having 2 run() methods on a single object,
    //check Main and gamelist for more info
    public Mistakefactory() {}
    public Mistakefactory( gamelist gamelist0 ) {this.gamelist1 = gamelist0;}



    @Override
    public void run() { this.gamelist1.cleandoom();}
}
