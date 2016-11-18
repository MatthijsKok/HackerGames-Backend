package com.hackergames.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Internet
{
    static final String URL_BASE = "https://hackathon.dominos.cloud/";


    // HTTP GET request
    public static String sendGet(String urlBase, String urlExtension) throws IOException
    {
        URL url = new URL(urlBase + urlExtension);

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")))
        {
            for (String line; (line = reader.readLine()) != null; )
            {
                sb.append(line);
            }
        }

        return sb.toString();
    }

    public static String sendGet(String urlExtension) throws Exception
    {
        return sendGet(URL_BASE, urlExtension);
    }


    // HTTP POST request
    public static String sendPost(String urlBase, String urlExtension, JSONObject object) throws IOException
    {
        URL url = new URL(urlBase + urlExtension);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
        out.write(object.toString());
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();

        Scanner s = new Scanner(httpCon.getInputStream()).useDelimiter("\\A");
        String x = s.next();
        s.close();
        return x;
    }

    public static String sendPost(String urlExtension, JSONObject object) throws Exception
    {
        return sendPost(URL_BASE, urlExtension, object);
    }
}
