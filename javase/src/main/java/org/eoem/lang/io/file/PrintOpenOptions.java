package org.eoem.lang.io.file;

import java.nio.file.StandardOpenOption;

public class PrintOpenOptions {
    public static void main(String[] args) {
        StandardOpenOption[] values = StandardOpenOption.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
    }
}
