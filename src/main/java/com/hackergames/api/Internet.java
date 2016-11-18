package com.hackergames.api;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet
{
    static final String URL_BASE = "https://hackathon.dominos.cloud/";

    // HTTP GET request
    public static String sendGet(String urlBase, String urlExtension) throws Exception {
        URL obj = new URL(urlBase + urlExtension);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        // Read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();
    }

    public static String sendGet(String urlExtension) throws Exception {
        return sendGet(URL_BASE, urlExtension);
    }

    // HTTP POST request
    public static String sendPost(String urlBase, String urlExtension, JSONObject object) throws Exception {
        URL obj = new URL(urlBase + urlExtension);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(object.toString());
        wr.flush();
        wr.close();

        // Read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
        {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

    public static String sendPost(String urlExtension, JSONObject object) throws Exception {
        return sendPost(URL_BASE, urlExtension, object);
    }
}