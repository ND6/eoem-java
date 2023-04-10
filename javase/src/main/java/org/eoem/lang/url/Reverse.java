package org.eoem.lang.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 读取和写入 URLConnection
 * 创建 URL。
 * 获取 URLConnection 对象。
 * 在 URLConnection 上设置输出功能。
 * 打开与资源的连接。
 * 从连接获取输出流。
 * 写入输出流。
 * 关闭输出流。
 */
public class Reverse {
    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage:  java Reverse "
                    + "http://<location of your servlet/script>"
                    + " string_to_reverse");
            System.exit(1);
        }
        
        String stringToReverse = URLEncoder.encode(args[1], "UTF-8");
        
        URL url = new URL(args[0]);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        
        OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();
    }
}