package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class listener implements Runnable {

    public ConcurrentHashMap<String, Session> sessionConcurrentHashMap;
    int socket1;

    SqlRobot sql1 = new SqlRobot();

    public listener() {
    }

    public listener(
            int socket, ConcurrentHashMap<String, Session> sessionConcurrentHashMap1) {
        this.socket1 = socket;
        this.sessionConcurrentHashMap = sessionConcurrentHashMap1;
    }

    //adds a new game
    public void addsession(String gameid) throws SQLException {
        this.sessionConcurrentHashMap.put(gameid, new Session(gameid));
        this.sessionConcurrentHashMap.get(gameid).RNGRobot1.preparedecks();
        this.sessionConcurrentHashMap.get(gameid).createQuestion();

    }

    public void run() {
//this method listens to the socket
        try (ServerSocket ss = new ServerSocket(socket1)) {
            while (1 > 0) {

                Socket s = ss.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());


                String Paloki = din.readUTF();
                String[] Palokiz = Paloki.split("spergzilion");

                dout.writeUTF(filtertheneedful(Palokiz));

                dout.flush();
            }
        } catch (IOException | SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }

    //this basically filters what is received in the run method
//and reacts accordingly
    // 1mode 2creator 3payload 4extra payload

    public String filtertheneedful(String[] TcpMessage) throws SQLException {

        if (this.sessionConcurrentHashMap.containsKey(TcpMessage[1]))
        ///////////////
        {
            if (TcpMessage[0].equals("getscore"))
            {return this.sessionConcurrentHashMap.get(TcpMessage[1]).getScores();}
            else if (TcpMessage[0].equals("getanswers")) {
                return this.sessionConcurrentHashMap.get(TcpMessage[1]).getAnswers();
            }
            else if (TcpMessage[0].equals("getquestion")) {
                return this.sessionConcurrentHashMap.get(TcpMessage[1]).getQuestion();
            }
            else if (TcpMessage[0].equals("addplayer")) {

              //  if (this.sessionConcurrentHashMap.get(1).adhoccheck().contains(TcpMessage[2])) { return " they are already here jim "; } else
                {this.sessionConcurrentHashMap.get(TcpMessage[1]).addplayer(TcpMessage[2]);
                //this.sessionConcurrentHashMap.get(TcpMessage[1]).setDoomsdayclock("no");
                System.out.println(this.sessionConcurrentHashMap.get(TcpMessage[1]).players.size());
                return "Player inserted";
                }
            } else if (TcpMessage[0].equals("vote")) {

                if (TcpMessage[2].equals(TcpMessage[3]))
                {this.sessionConcurrentHashMap.get(TcpMessage[1]).d20roll();} else {
                    System.out.println("1");
                    this.sessionConcurrentHashMap.get(TcpMessage[1]).incrementScore(TcpMessage[2], TcpMessage[3]);
                    System.out.println("2");
                    this.sessionConcurrentHashMap.get(TcpMessage[1]).createQuestion();
                    System.out.println("3");
                    this.sessionConcurrentHashMap.get(TcpMessage[1]).setDoomsdayclock("no");
                    System.out.println("4");

                }
            } else if (TcpMessage[0].equals("answer")) {
                this.sessionConcurrentHashMap.get(TcpMessage[1]).insAnswer(TcpMessage[2], TcpMessage[3]);
            }            return " it doesn't exist ";}
        //////////////

        else if (TcpMessage[0].equals("addsession")) {


            if (this.sessionConcurrentHashMap.containsKey(TcpMessage[1])) {
                return " game already exists ";
            } else {


                this.addsession(TcpMessage[1]);
                this.sessionConcurrentHashMap.get(TcpMessage[1]).addplayer(TcpMessage[2]);
                this.sessionConcurrentHashMap.get(TcpMessage[1]).setElectorcount(TcpMessage[2]);
                if (this.sessionConcurrentHashMap.contains(TcpMessage[1]))
                {System.out.println("i am alive");}
                if (this.sessionConcurrentHashMap.containsKey(TcpMessage[1]))
                {System.out.println("derp");}
            }
        } else if (TcpMessage[0].equals("questionbatch")) {
            String[] Yormungandr = TcpMessage[2].split("yormungandryormungandr");
            ArrayList<String> Asgard = new ArrayList<String>();
            for (int i = 0; i < Yormungandr.length; i++) {
                Asgard.add(Yormungandr[i]);
            }
            sql1.big_Questions_INSERT(Asgard, TcpMessage[1]);
            return " inserted questions ";
        } else if (TcpMessage[0].equals("situationbatch")) {
            String[] Yormungandr = TcpMessage[2].split("yormungandryormungandr");
            ArrayList<String> Asgard = new ArrayList<String>();
            for (int i = 0; i < Yormungandr.length; i++) {
                Asgard.add(Yormungandr[i]);
            }
            sql1.big_Situations_INSERT(Asgard, TcpMessage[1]);
            return "inserted situations";
        } else {
            // 1mode 2creator 3payload
            //return "yinz fucked up";
        }

        return " Did a thing ";
    }
    //}

    //every 15 minutes this does some light cleaning by virtue of this being Mistakefactory's run method
    public void cleandoom() throws InterruptedException {
        {TimeUnit.MINUTES.sleep(15);

            this.sessionConcurrentHashMap.forEach((k, v) ->
            {
                if (this.sessionConcurrentHashMap.get(k).getDoomsdayclock().equals("no")) {
                    this.sessionConcurrentHashMap.get(k).setDoomsdayclock("yes");
                } else if (this.sessionConcurrentHashMap.get(k).getDoomsdayclock().equals("yes")) {
                    sessionConcurrentHashMap.remove(k);
                }
                try {
                    TimeUnit.MINUTES.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}


