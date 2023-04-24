package org.eoem.lang.io.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Globbing {
    public static void main(String[] args) {
    
    }
    
    /**
     * 使用 Globbing 过滤目录列表
     */
    public static void test1() {
        Path dir = Paths.get("javase/src/main/java/org/eoem/lang/io/file");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{java,class,jar}")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }
    
    
    /**
     * 编写自己的目录过滤器
     */
    public static void test2() {
        Path dir = Paths.get("javase/src/main/java/org/eoem/lang/io/file");
        Path path = Paths.get("javase/src/main/java/org/eoem/lang/io/file");
        DirectoryStream.Filter<Path> filter =
                Files::isDirectory;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }
}
