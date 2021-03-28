package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//this is the object that does our SQL querries, it has a bunch of
//code related to sql, and also a method directly ported from the desktop version that directly uploads to the
//sql server from a file location on the hdd

public class SqlMinion {

    static String IP = "144.202.15.194";
    static String USER = "TITS";
    static String PASS = "TITS";
    static String DB_URL = "jdbc:mysql://144.202.15.194:3306/teopolis";

    public SqlMinion() {
    }


    public void insertQUESTION(String Text1, String User1) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        PreparedStatement bullet1 = conn.prepareStatement("INSERT INTO questions (Text, creator) VALUES (?, ?)");
        bullet1.setString(1, Text1);
        bullet1.setString(2, User1);
        bullet1.executeUpdate();
        System.out.println("Uploading the thing");
        conn.close();
        System.out.println("Thing uploaded!");
    }

    public void insertSituation(String Text1, String User1) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        PreparedStatement bullet1 = conn.prepareStatement("INSERT INTO situations (Text, creator) VALUES (?, ?)");
        bullet1.setString(1, Text1);
        bullet1.setString(2, User1);
        bullet1.executeUpdate();
        System.out.println("Uploading the thing");
        conn.close();
        System.out.println("Thing uploaded!");
    }

    public String pull_QUESTION(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<String> arrayList1 = new ArrayList();
        Statement stmt = conn.createStatement();
        PreparedStatement bullet1 = conn.prepareStatement("SELECT Text FROM questions WHERE id = ?");
        bullet1.setInt(1, id);
        ResultSet rs = bullet1.executeQuery();
        while (rs.next()) {System.out.println(rs.getString("Text"));
            arrayList1.add(rs.getString(1));}
        conn.close();
        return arrayList1.get(0);

    }
    public String SPQ  (int id) throws SQLException {{
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<String> arrayList1 = new ArrayList();
        Statement stmt = conn.createStatement();
        PreparedStatement bullet1 = conn.prepareStatement("SELECT Text FROM questions WHERE id = ?");
        bullet1.setInt(1, id);
        ResultSet rs = bullet1.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Text"));
            arrayList1.add(rs.getString(1));

        }
        conn.close();
        return String.valueOf(arrayList1.get(0));}}


    public String pull_SITUATION(int id) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<String> arrayList1 = new ArrayList();
        Statement stmt = conn.createStatement();
        PreparedStatement bullet1 = conn.prepareStatement("SELECT Text FROM situations WHERE id = ?");
        bullet1.setInt(1, id);
        ResultSet rs = bullet1.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("Text"));
            arrayList1.add(rs.getString(1));
        }
        conn.close();
        return arrayList1.get(0);
    }


    public int questSIZE() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<Integer> arrayList1 = new ArrayList();
        Statement stmt = conn.createStatement();
        PreparedStatement bullet1 = conn.prepareStatement("SELECT COUNT(id) FROM questions");

        ResultSet rs = bullet1.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            arrayList1.add(rs.getInt(1));
        }

        conn.close();

        return arrayList1.get(0);
    }

    public int sitsSIZE() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        ArrayList<Integer> arrayList1 = new ArrayList();

        Statement stmt = conn.createStatement();
        PreparedStatement bullet1 = conn.prepareStatement("SELECT COUNT(id) FROM situations");

        ResultSet rs = bullet1.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            arrayList1.add(rs.getInt(1));
        }
        conn.close();
        return arrayList1.get(0);
    }

    public void comboQQQ_BIG_insert(String file , String Creator ) throws FileNotFoundException, SQLException {
        File file1 = new File(file);
        Scanner sc = new Scanner(file1);
        ArrayList<String> Bonerstorm = new ArrayList<>();
        while (sc.hasNextLine()) {
            Bonerstorm.add(sc.nextLine());
        }
        big_Questions_INSERT(Bonerstorm , Creator);
    }


    public void big_Questions_INSERT(ArrayList<String> Payload, String Creator) throws SQLException {
        for (int i = 0; i < Payload.size(); i++)
        {insertQUESTION(Payload.get(i), Creator);}


    }

    public void comboSSS_BIG_insert(String file , String Creator ) throws FileNotFoundException, SQLException {
        File file1 = new File(file);
        Scanner sc = new Scanner(file1);
        ArrayList<String> Bonerstorm = new ArrayList<>();
        while (sc.hasNextLine()) {
            Bonerstorm.add(sc.nextLine());
        }
        big_Situations_INSERT(Bonerstorm , Creator);
    }




    public void big_Situations_INSERT(ArrayList<String> Payload, String Creator) throws SQLException {
        for (int i = 0; i < Payload.size(); i++) {
            insertSituation(Payload.get(i), Creator);
        }
    }

}