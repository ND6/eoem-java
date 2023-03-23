package com.eoem.widgets;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;

public class HowToImageMetaData {
    
    public static void main(String[] args) throws ImageProcessingException, IOException {
        printExif(new File("D:/tt/waterf.jpg"));
    }
    
    // 遍历打印图片文件的EXIF信息
    private static void printExif(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println(tag + " = " + tag.getDescription());
            }
        }
    }
}
