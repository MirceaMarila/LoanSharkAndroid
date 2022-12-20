package com.example.loansharkfe.util;

import com.example.loansharkfe.config.HttpConfig;
import com.example.loansharkfe.constants.HTTPMethods;
import com.example.loansharkfe.dto.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Request {

    private static Request request = new Request();

    public static Request getInstance() {
        return request;
    }

    private final Json json;

    private Request() {
        this.json = Json.getInstance();
    }

    public GenericResponse get(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.GET, body_object);
    }

    public GenericResponse post(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.POST, body_object);
    }

    public GenericResponse head(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.HEAD, body_object);
    }

    public GenericResponse options(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.OPTIONS, body_object);
    }

    public GenericResponse put(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.PUT, body_object);
    }

    public GenericResponse delete(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.DELETE, body_object);
    }

    public GenericResponse trace(String url, Object body_object) throws IOException {
        return makeRequest(url, HTTPMethods.TRACE, body_object);
    }

    public GenericResponse makeRequest(String url, HTTPMethods httpMethod, Object body_object) throws IOException {
        //TODO("Check if method works if you give an empty body_object.
        // Make another similar method without body_object stuff if it doesn't or if else the crap out of this one")

        //TODO("Check if method works if received body is empty.
        // Make another similar method without receiving body stuff if it doesn't or if else the crap out of this one")

        URL url_object = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url_object.openConnection();
        connection.setConnectTimeout(HttpConfig.connectionTimeout);
        connection.setReadTimeout(HttpConfig.connectionReadTimeout);
        connection.setRequestMethod(httpMethod.toString());
        connection.setRequestProperty("Authorization", "");
        connection.setRequestProperty("Content-TYpe", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        String objectAsString = json.objectMapper.writeValueAsString(body_object);

        OutputStream outputStream = connection.getOutputStream();
        byte[] body = objectAsString.getBytes("utf-8");
        outputStream.write(body, 0, body.length);

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;

        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        GenericResponse genericResponse = new GenericResponse(
                connection.getResponseCode(),
                connection.getResponseMessage(),
                HTTPMethods.valueOf(connection.getRequestMethod()),
                response.toString()
        );

        connection.disconnect();
        return genericResponse;
    }

}
