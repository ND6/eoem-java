package org.eoem.lang.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HowToLinks {
    public static void main(String[] args) {
//        createSymbolicLinks();
        createLinks();
    }
    
    //创建符号链接
    public static void createSymbolicLinks() {
        Path newLink = Paths.get("xanaduSymbolicLink");
        Path target = Paths.get("xanadu.txt");
        try {
            Files.createSymbolicLink(newLink, target);
        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not support symbolic links.
            System.err.println(x);
        }
    }
    
    //创建硬链接
    public static void createLinks() {
        Path newLink = Paths.get("xanaduLink");
        Path target = Paths.get("xanadu.txt");
        try {
            Files.createSymbolicLink(newLink, target);
        } catch (IOException x) {
            System.err.println(x);
        } catch (UnsupportedOperationException x) {
            // Some file systems do not support symbolic links.
            System.err.println(x);
        }
    }
}
