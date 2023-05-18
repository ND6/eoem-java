package org.eoem.pdf;

import lombok.SneakyThrows;
import lombok.var;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PdfToBase64Converter {
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//        var pdf = "D:/文档/一体机项目/法律文书打印/浙江省知识产权保护和促进条例.pdf";
        var pdf = "D:\\文档\\一体机项目\\合同文本一键生成\\htwbyjsc.pdf";


//        var pdf = "https://nbbs.gat.zj.gov.cn/machine/minio/get/11683863c61f404484687f8285dc08ac_%E9%98%B2%E8%8C%83%E5%92%8C%E5%A4%84%E7%BD%AE%E9%9D%9E%E6%B3%95%E9%9B%86%E8%B5%84%E6%9D%A1%E4%BE%8B.pdf";
        
        String encodedUrl = URLEncoder.encode("11683863c61f404484687f8285dc08ac_防范和处置非法集资条例.pdf", "UTF-8");
//        String pdf = "https://nbbs.gat.zj.gov.cn/machine/minio/get/"+encodedUrl;
        String outputFolder = "D:\\tt\\temp\\";
        
        var list = convertPdfToBase64(pdf);
        System.out.println("LIst count: " + list.size());
        var i = 0;
        for (var p : list
        ) {
            System.out.println("### page :");
            System.out.println(p);
            try {
                saveBase64AsImage(p, outputFolder + "htwbyjsc_" + (i++) + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    private static void saveBase64AsImage(String base64Image, String outputFilePath) throws IOException {
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            fos.write(imageBytes);
        }
    }
    
    @SneakyThrows
    public static List<String> convertPdfToBase64(String filePath) {
        
        List<String> base64Images = new ArrayList<>();
        
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            
            int numPages = document.getNumberOfPages();
            
            for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                baos.flush();
                
                // 将图片转换为Base64字符串
                String base64Image = javax.xml.bind.DatatypeConverter.printBase64Binary(baos.toByteArray());
                base64Images.add(base64Image);
                
                baos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return base64Images;
    }
    
    @SneakyThrows
    public static List<String> convertPdfToBase64WithUrl(String url) {
        
        List<String> base64Images = new ArrayList<>();
        
        try (PDDocument document = PDDocument.load(new URL(url).openStream())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            
            int numPages = document.getNumberOfPages();
            
            for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                baos.flush();
                
                // 将图片转换为Base64字符串
                String base64Image = javax.xml.bind.DatatypeConverter.printBase64Binary(baos.toByteArray());
                base64Images.add(base64Image);
                
                baos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return base64Images;
    }
    
    public static InputStream getFileContent(String fileUrl) {
        try {
            URL url2 = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.connect();
            return conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static InputStream getInputStreamFromUrl(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return new BufferedInputStream(connection.getInputStream());
        } else {
            throw new IOException("Failed to get input stream from URL. Response code: " + responseCode);
        }
    }
}
