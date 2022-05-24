package com.company;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;


class MyHttpHandler implements HttpHandler {




    listener listener1= new listener (  );
    public listener getListener1() {
        return listener1;
    }

    public void setListener1(listener listener1) {
        this.listener1 = listener1;
    }
    public MyHttpHandler(){}
  //public MyHttpHandler ( listener listener1){ this.listener1=listener1;}

    @Override
    public void handle(HttpExchange t) throws IOException {

        InputStream ios = t.getRequestBody();

        byte[] input = ios.readAllBytes();
        String inputString = new String(input, StandardCharsets.UTF_8);
        String[] Paloki = inputString.split("spergzilion");
        request response = new request();
        response.setPayload2("paloki");
        if (Paloki[0].equals("existinggame")) {
            try {

                request request1 = new request(
                        Paloki[0], Paloki[1], Paloki[2],
                        Paloki[3], Paloki[4], Paloki[5],
                        Paloki[6], Paloki[7]);

                response.setPayload2(handleResponse(request1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (Paloki[0].equals("creategame")) {
            request request1 = new request();
            request1.setController("creategame");
            request1.setMethodname("addsession");
            request1.setGameid(Paloki[1]);
            request1.setGamepassword(Paloki[2]);
            request1.setExpansion(Paloki[3]);
            request1.setPlayername(Paloki[4]);
            request1.setPlayerPassword(Paloki[5]);

            try {
                response.setPayload2(handleResponse(request1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (Paloki[0].equals("createNewCards")) {
            request request2 = new request();
            request2.setController("createNewCards");
            request2.setMethodname(Paloki[1]);
            request2.setAuthor(Paloki[2]);
            request2.setAuthorPassword(Paloki[3]);
            request2.setExpansion(Paloki[4]);
            request2.setPayload1(Paloki[5]);

            try {
                response.setPayload2(handleResponse(request2));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        t.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        t.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        t.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        t.sendResponseHeaders(200, response.getPayload2().getBytes().length);

        OutputStream os = t.getResponseBody();
        os.write(response.getPayload2().getBytes());
        os.close();
    }


    private String handleResponse(request request1) throws IOException, SQLException {

        return this.listener1.filtertheneedful1111(request1);

    }

}
