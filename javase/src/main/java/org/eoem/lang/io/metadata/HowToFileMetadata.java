package org.eoem.lang.io.metadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

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
            BasicFileAttributes basicFileAttributes = Files.readAttributes(file, BasicFileAttributes.class);
    
            System.out.println("creationTime: " + basicFileAttributes.creationTime());
            System.out.println("lastAccessTime: " + basicFileAttributes.lastAccessTime());
            System.out.println("lastModifiedTime: " + basicFileAttributes.lastModifiedTime());
    
            System.out.println("isDirectory: " + basicFileAttributes.isDirectory());
            System.out.println("isOther: " + basicFileAttributes.isOther());
            System.out.println("isRegularFile: " + basicFileAttributes.isRegularFile());
            System.out.println("isSymbolicLink: " + basicFileAttributes.isSymbolicLink());
            System.out.println("size: " + basicFileAttributes.size());
            System.out.println("attr.fileKey(): " + basicFileAttributes.fileKey());
    
            long currentTime = System.currentTimeMillis();
            FileTime ft = FileTime.fromMillis(currentTime);
            Files.setLastModifiedTime(file, ft);
    
            PosixFileAttributes posixFileAttributes =
                    Files.readAttributes(file, PosixFileAttributes.class);
            System.out.format("posixFileAttributes:%s %s %s%n",
                    posixFileAttributes.owner().getName(),
                    posixFileAttributes.group().getName(),
                    PosixFilePermissions.toString(posixFileAttributes.permissions()));
    
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
