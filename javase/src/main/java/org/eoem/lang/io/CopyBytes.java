package org.eoem.lang.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {
        
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try {
//            in = new FileInputStream("xanadu.txt");
            in = new FileInputStream("D:\\code\\eoem-java\\javase\\src\\main\\resources\\xanadu.txt");
//            out = new FileOutputStream("outagain.txt");
            out = new FileOutputStream("D:\\code\\eoem-java\\javase\\src\\main\\resources\\outagain.txt");
            int c;
            
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}