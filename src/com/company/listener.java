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


    public ConcurrentHashMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<String, Session>();
    int socket1;

    SqlRobot sql1 = new SqlRobot();

    public listener() {
    }

    public listener(
            int socket, ConcurrentHashMap<String, Session> sessionConcurrentHashMap1) {
        this.socket1 = socket;
        this.sessionConcurrentHashMap = sessionConcurrentHashMap1;
    }

    public ConcurrentHashMap<String, Session> getSessionConcurrentHashMap() {
        return sessionConcurrentHashMap;
    }

    public void setSessionConcurrentHashMap(ConcurrentHashMap<String, Session> sessionConcurrentHashMap) {
        this.sessionConcurrentHashMap = sessionConcurrentHashMap;
    }

    //adds a new game
    public void addsession(String gameid, String password, String expansionname) throws SQLException {

        this.sessionConcurrentHashMap.put(gameid, new Session(gameid));

        if (expansionname.equals("all")) {
            this.sessionConcurrentHashMap.get(gameid).RNGRobot1.preparedecks();
        } else {
            this.sessionConcurrentHashMap.get(gameid).RNGRobot1.customPreparation(expansionname);
        }
        this.sessionConcurrentHashMap.get(gameid).createQuestion();
        this.sessionConcurrentHashMap.get(gameid).setPassword(password);

    }

    public void run() {
//this method listens to the socket


        try (ServerSocket ss = new ServerSocket(socket1)) {
            while (1 > 0) {
                System.out.println(socket1);

                Socket s = ss.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());


                String Paloki = din.readUTF();
                String[] Palokiz = Paloki.split("spergzilion");

                dout.writeUTF(filtertheneedful1111(new request()));

                dout.flush();
                try {
                    s.close();
                    din.close();
                    dout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        } catch (IOException | SQLException exc) {
            System.out.println(exc.getMessage());
        }


    }


    //this basically filters what is received in the run method
    //and reacts accordingly
// check the request class for more info
    public String filtertheneedful1111(request request1) throws SQLException {

        sessionConcurrentHashMap.forEach((k, v) ->
                { System.out.println(sessionConcurrentHashMap.get(k).getGameid() +sessionConcurrentHashMap.get(k).getDoomsdayclock() ); });

        if (request1.getController().equals("existinggame")) {
            if (this.sessionConcurrentHashMap.containsKey(request1.getGameid())) {

                if (this.sessionConcurrentHashMap.get(request1.getGameid()).getPassword().equals(request1.getGamepassword())) {
                    if (request1.getMethodname().equals("addplayer")) {

                        if (this.sessionConcurrentHashMap.get(request1.getGameid()).adhoccheck().contains(request1.getPlayername())

                        ) {
                            return " they are already here jim ";
                        } else {
                            this.sessionConcurrentHashMap.get(request1.getGameid()).addplayer(request1.getPlayername(), request1.getPlayerPassword());
                            return "Player inserted";
                        }
                    }
                    {
                        if (this.sessionConcurrentHashMap.get(request1.getGameid()).checkIfPlayerPresent(request1.getPlayername(), request1.getPlayerPassword())
                                &&
                                this.sessionConcurrentHashMap.get(request1.getGameid()).getPassword().equals(request1.getGamepassword()));
                        {
                            if (request1.getMethodname().equals("getresults")) {
                                return this.sessionConcurrentHashMap.get(request1.getGameid()).getresults();
                            } else if (request1.getMethodname().equals("getjudge")) {
                                return this.sessionConcurrentHashMap.get(request1.getGameid()).getjudge();
                            } else if (request1.getMethodname().equals("getquestion")) {
                                return this.sessionConcurrentHashMap.get(request1.getGameid()).getQuestion();
                            } else if (request1.getMethodname().equals("vote")
                                    &&
                                    (this.sessionConcurrentHashMap.get(request1.getGameid()).getElectorcount().equals(request1.getPlayername()))
                                    &&
                                    (this.sessionConcurrentHashMap.get(request1.getGameid()).getJudgepassword().equals(request1.getPlayerPassword()))
                            ) {


                                if (request1.getPayload1().equals(request1.getPlayername())) {
                                    this.sessionConcurrentHashMap.get(request1.getPlayername()).d20roll();
                                } else {
                                    System.out.println("1");
                                    this.sessionConcurrentHashMap.get(request1.getGameid()).incrementScore(request1.getPlayername(), request1.getPayload1());
                                    System.out.println("2");
                                    this.sessionConcurrentHashMap.get(request1.getGameid()).createQuestion();
                                    System.out.println("3");

                                    this.sessionConcurrentHashMap.get(request1.getGameid()).setDoomsdayclock("no");
                                    System.out.println("4");

                                }
                            } else if (request1.getMethodname().equals("answer")) {
                                this.sessionConcurrentHashMap.get(request1.getGameid()).insAnswer(request1.getPlayername(), request1.getPayload1());
                                return "answer inserted";
                            }

                        }
                    }
                }
            }
        } else if (request1.getController().equals("creategame")) {
            if (request1.getMethodname().equals("addsession")) {


                if (this.sessionConcurrentHashMap.containsKey(request1.getGameid())) {
                    return " game already exists ";
                } else {
                    this.addsession(request1.getGameid(), request1.getGamepassword(), request1.getExpansion());
                    this.sessionConcurrentHashMap.get(request1.getGameid()).addplayer(request1.getPlayername(), request1.getPlayerPassword());
                    this.sessionConcurrentHashMap.get(request1.getGameid()).setElectorcount(request1.getPlayername());
                    this.sessionConcurrentHashMap.get(request1.getGameid()).setJudgepassword(request1.getPlayerPassword());

                }
            }
        } else if (request1.getController().equals("createNewCards")) {
            if (request1.getMethodname().equals("questionbatch")) {
                if (this.sql1.pullproducers(request1.getAuthor(), request1.getAuthorPassword())) {
                    String[] Yormungandr = request1.getPayload1().split("yormungandryormungandr");
                    ArrayList<String> Asgard = new ArrayList<String>();
                    for (int i = 0; i < Yormungandr.length; i++) {
                        Asgard.add(Yormungandr[i]);
                    }
                    sql1.big_Questions_INSERT(Asgard, request1.getExpansion());
                }
                return " inserted questions ";
            } else if (request1.getMethodname().equals("situationbatch")) {
                if (this.sql1.pullproducers(request1.getAuthor(), request1.getAuthorPassword())) {
                    String[] Yormungandr = request1.getPayload1().split("yormungandryormungandr");
                    ArrayList<String> Asgard = new ArrayList<String>();
                    for (int i = 0; i < Yormungandr.length; i++) {
                        Asgard.add(Yormungandr[i]);
                    }
                    sql1.big_Situations_INSERT(Asgard, request1.getExpansion());
                }
                return "inserted situations";
            }
        }


        return " Did a thing ";
    }


    //every 15 minutes this does some light cleaning by virtue of this being Mistakefactory's run method
    public void cleandoom() throws InterruptedException {
       while (1>0){   System.out.println("KILL KILL KILL");
            TimeUnit.MINUTES.sleep(10);

            this.sessionConcurrentHashMap.forEach((k, v) ->
            {
                if (this.sessionConcurrentHashMap.get(k).getDoomsdayclock().equals("no")) {
                    this.sessionConcurrentHashMap.get(k).setDoomsdayclock("yes");
                } else if (this.sessionConcurrentHashMap.get(k).getDoomsdayclock().equals("yes")) {

                    System.out.println("removed game: "+sessionConcurrentHashMap.get(k).getGameid());
                    sessionConcurrentHashMap.remove(k);
                }
            });
        }
    }


}


