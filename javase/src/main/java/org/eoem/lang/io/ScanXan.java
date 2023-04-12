package org.eoem.lang.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScanXan {
    public static void main(String[] args) throws IOException {
        
        Scanner s = null;
        
        try {
            File file = new File("xanadu.txt");
            System.out.println(file.getAbsolutePath());
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));
            
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}