package org.eoem.lang.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
    public static void main(String[] args) throws IOException {
        
        FileReader inputStream = null;
        FileWriter outputStream = null;
        
        try {
            inputStream = new FileReader("D:\\code\\eoem-java\\javase\\src\\main\\resources\\xanadu.txt");
            outputStream = new FileWriter("D:\\code\\eoem-java\\javase\\src\\main\\resources\\characteroutput.txt");
//            BufferedReader inputStream2 = new BufferedReader(new FileReader("xanadu.txt"));
//            BufferedWriter outputStream2 = new BufferedWriter(new FileWriter("characteroutput.txt"));
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
    
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}