package org.eoem.lang.io.other;

import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public class HowToDefaultFileSystem {
    public static void main(String[] args) {
        //要获取默认文件系统，请使用 getDefault 方法。通常，这个 FileSystems 方法(注意复数)被链接到 FileSystem 方法之一(注意单数)
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.*");
        System.out.println(matcher.toString());
        System.out.println(matcher.hashCode());
        // 路径字符串分隔符
        String separator = File.separator;
        String separator2 = FileSystems.getDefault().getSeparator();
        System.out.println(separator);
        System.out.println(separator2);
        //文件系统的文件储存
        for (FileStore store : FileSystems.getDefault().getFileStores()) {
            String n = store.name();
            System.out.println(n);
        }
    }
}
