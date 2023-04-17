package org.eoem.lang.io.path;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class HowToPath {
    
    public static void main(String[] args) {
        test1();
    }
    
    public static void test1() {
        Path path = Paths.get("/code/joe/foo");
        Path otherPath = Paths.get("/code");
        Path beginning = Paths.get("/code");
        Path ending = Paths.get("foo");
        
        if (path.equals(otherPath)) {
            // equality logic here
            System.out.println(path.toAbsolutePath());
        } else if (path.startsWith(beginning)) {
            // path begins with "/home"
            System.out.println(path.toString());
        } else if (path.endsWith(ending)) {
            // path ends with "foo"
            System.out.println(path.toString());
        }
        for (Path name : path) {
            System.out.println(name);
        }
        System.out.println("### Iterator");
        
        Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()) {
            Path p = iterator.next();
            System.out.println(p);
            System.out.println(p.toAbsolutePath());
        }
    }
}
