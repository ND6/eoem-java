package org.eoem.lang.io.metadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class HowToFileMetadata {
    public static void main(String[] args) {
        testMetadata();
    }
    
    private static void testMetadata() {
        Path path = Paths.get("/test");
        try {
            System.out.println("Files.size " + Files.size(path));
            
            System.out.println("Files.isDirectory " + Files.isDirectory(path));
            
            System.out.println("Files.isRegularFile " + Files.isRegularFile(path));
            
            System.out.println("Files.isSymbolicLink " + Files.isSymbolicLink(path));
            
            System.out.println("Files.getLastModifiedTime " + Files.getLastModifiedTime(path));
            System.out.println("Files.getOwner " + Files.getOwner(path));
            System.out.println("Files.getAttribute " + Files.getAttribute(path, "size"));
            
            Path file = Paths.get("/test/fd1.json");
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            
            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size());
            System.out.println("attr.fileKey(): " + attr.fileKey());
            
            long currentTime = System.currentTimeMillis();
            FileTime ft = FileTime.fromMillis(currentTime);
            Files.setLastModifiedTime(file, ft);
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
