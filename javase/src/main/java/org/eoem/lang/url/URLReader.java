package org.eoem.lang.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {
    public static void main(String[] args) throws Exception {
        
        URL host = new URL("https://www.baidu.com/");
        BufferedReader in = new BufferedReader(new InputStreamReader(host.openStream(), "GBK"));
        
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        
        // 2
        try {
            URL myURL = new URL("https://www.baidu.com/");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
        } catch (MalformedURLException e) {
            // new URL() failed
            // ...
        } catch (IOException e) {
            // openConnection() failed
            // ...
        }
    }
}