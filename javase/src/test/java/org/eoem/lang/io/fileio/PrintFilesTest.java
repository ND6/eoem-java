package org.eoem.lang.io.fileio;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class PrintFilesTest {
    
    @Test
    void testPrintFiles() throws Exception {
        Path file = Paths.get("");
        System.out.println("AbsolutePath: " + file.toAbsolutePath());
        Files.walkFileTree(file, new PrintFiles());
    }
    
    @Test
    void visitFile() {
    }
    
    @Test
    void postVisitDirectory() {
    }
    
    @Test
    void visitFileFailed() {
    }
}