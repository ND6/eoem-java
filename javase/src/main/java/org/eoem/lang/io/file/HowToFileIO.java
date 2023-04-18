package org.eoem.lang.io.file;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class HowToFileIO {
    public static void main(String[] args) {
    
    }
    
    // ----------------------------------------------------------------
    
    /**
     * 小文件的常用方法
     */
    @SneakyThrows
    public static void test1() {
        // 从文件中读取所有字节或行
        Path file = Paths.get("xanadu.txt");
        byte[] fileArray;
        fileArray = Files.readAllBytes(file);
        
        // 将所有字节或行写入文件
        byte[] buf = new byte[fileArray.length];
        Files.write(file, buf);
    }
    
    // 文本文件的缓冲I/O 方法
    public static void test2() {
        Path file = Paths.get("xanadu.txt");
        // 使用缓冲流I/O读取文件
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        // 使用缓冲流I/O写入文件
        String s = "...";
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write(s, 0, s.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        //--------------------------------------------------------
        // 无缓冲流的方法和可与 java.io API 互操作的方法
        
        // 使用流 I/O 读取文件
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        
        // 使用流 I/O 创建和写入文件
        String s2 = "Hello World! ";
        byte data[] = s2.getBytes();
        Path p = Paths.get("./logfile.txt");
        
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
    
    // 用于 Channels 和 ByteBuffers 的方法
    public static void testChannel() {
        Path file = Paths.get("xanadu.txt");
        // Defaults to READ
        try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
            ByteBuffer buf = ByteBuffer.allocate(10);
            
            // Read the bytes with the proper encoding for this platform.  If
            // you skip this step, you might see something that looks like
            // Chinese characters when you expect Latin-style characters.
            String encoding = System.getProperty("file.encoding");
            while (sbc.read(buf) > 0) {
                buf.rewind();
                System.out.print(Charset.forName(encoding).decode(buf));
                buf.flip();
            }
        } catch (IOException x) {
            System.out.println("caught exception: " + x);
        }
    }
}
