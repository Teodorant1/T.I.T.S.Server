package com.company;

public class Mistakefactory implements Runnable {



    listener listener1;
//this main purpose of this is to basically trick java into effectively having 2 run() methods on a single object,
    //check Main and gamelist for more info
    public Mistakefactory() {}
    public Mistakefactory( listener listener0) {this.listener1 = listener0;}

    public listener getListener1() {
        return listener1;
    }

    public void setListener1(listener listener1) {
        this.listener1 = listener1;
    }

    @Override
    public void run() {
        try {
            this.listener1.cleandoom();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
