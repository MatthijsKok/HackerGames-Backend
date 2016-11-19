package com.hackergames.pizzas.services;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


/**
 * Sends requests to the API.
 */
public class Requester
{
    private static final String URL_BASE = "https://hackathon.dominos.cloud/";


    /**
     * Executes a GET request on the given endpoint.
     *
     * @param endpoint the Domino's API endpoint
     */
    public static JSONObject getRequest(final String endpoint) throws IOException
    {
        return getRequest(URL_BASE, endpoint);
    }

    /**
     * Executes a GET request on the given endpoint.
     *
     * @param domain   the domain name of the API to use
     * @param endpoint the Domino's API endpoint
     */
    public static JSONObject getRequest(final String domain, final String endpoint) throws IOException
    {
        final URL request = new URL(domain + endpoint);
        final JSONTokener tokener = new JSONTokener(request.openStream());
        return new JSONObject(tokener);
    }

    /**
     * Executes a POST request on the given endpoint, with the given data.
     *
     * @param endpoint   the Domino's API endpoint
     * @param parameters the data to POST
     */
    public static String postRequest(final String endpoint, final JSONObject parameters) throws IOException
    {
        return postRequest(URL_BASE, endpoint, parameters);
    }

    /**
     * Executes a POST request on the given endpoint, with the given data.
     *
     * @param domain     the domain name of the API to use
     * @param endpoint   the Domino's API endpoint
     * @param parameters the data to POST
     */
    public static String postRequest(final String domain, final String endpoint, final JSONObject parameters)
            throws IOException
    {
        final URLConnection connection = new URL(URL_BASE + endpoint).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream output = connection.getOutputStream())
        {
            output.write(parameters.toString().getBytes("UTF-8"));
        }

        final InputStream responseStream = connection.getInputStream();
        final Scanner responseScanner = new Scanner(responseStream).useDelimiter("\\A");
        final String response = responseScanner.next();
        responseScanner.close();
        return response;
    }
}
