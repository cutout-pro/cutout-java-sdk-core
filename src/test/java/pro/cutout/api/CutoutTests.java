package pro.cutout.api;


import com.alibaba.fastjson.JSON;
import pro.cutout.api.requests.*;
import pro.cutout.api.responses.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.UUID;

class CutoutTests {
    static String serverUrl = "https://picupapi.tukeli.net/api/v1";
    static String apikey = "d214a9f3ed4e47c08bed61e5a76cf255";
    static CutoutClient client = new CutoutClient(serverUrl, apikey);


    public static void main(String[] args) throws Exception {
        System.out.println("===============================================");
        fetchCreditBalance();
        System.out.println("===============================================");
    }

    public static void fetchCreditBalance() throws Exception {
        FetchCreditBalanceRequest request = new FetchCreditBalanceRequest();
        FetchCreditBalanceResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");

    }

    public static void photoAnimerTaskGet() throws Exception {
        PhotoAnimerTaskGetRequest request = new PhotoAnimerTaskGetRequest();
        request.setTaskId(296566L);
        PhotoAnimerTaskGetResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoAnimerTaskCreate() throws Exception {
        PhotoAnimerTaskCreateRequest request = new PhotoAnimerTaskCreateRequest();
        request.setImageUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        request.setTemplateId(0);
        PhotoAnimerTaskCreateResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");

    }

    public static void passportPhoto() throws Exception {
        String base64 = null;
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\root\\Desktop\\123.jpg")) {
            base64 = toBASE64(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PassportPhotoRequest request = new PassportPhotoRequest();
        request.setBase64(base64);
        request.setBgColor("3557FF");
        //request.setBgColor2();
        request.setDpi(300);
        request.setMmHeight(49);
        request.setMmWidth(35);
        request.setPrintBgColor("FFFCF9");
        request.setPrintMmHeight(297);
        request.setPrintMmWidth(210);
        request.setDress("woman3");
        PassportPhotoResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void cartoonSelfieToBase64ByUrl() throws Exception {
        CartoonSelfieToBase64ByUrlRequest request = new CartoonSelfieToBase64ByUrlRequest();
        request.setUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        request.setCartoonType(3);
        CartoonSelfieToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void cartoonSelfieToBase64ByFile() throws Exception {
        CartoonSelfieToBase64ByFileRequest request = new CartoonSelfieToBase64ByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        request.setCartoonType(3);
        CartoonSelfieToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void cartoonSelfieToByteByFile() throws Exception {
        CartoonSelfieToByteByFileRequest request = new CartoonSelfieToByteByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        request.setCartoonType(1);
        CartoonSelfieToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void imageRetouch() throws Exception {
        String base64 = null;
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\root\\Desktop\\aaaa.jpg")) {
            base64 = toBASE64(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageRetouchRequest.Rectangle rectangle = new ImageRetouchRequest.Rectangle();
        rectangle.setWidth(120);
        rectangle.setHeight(138);
        rectangle.setX(370);
        rectangle.setY(166);
        ImageRetouchRequest request = new ImageRetouchRequest();
        request.setBase64(base64);
        request.setRectangles(Arrays.asList(rectangle));
        ImageRetouchResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
    }

    public static void photoColorizerToBase64ByUrl() throws Exception {
        PhotoColorizerToBase64ByUrlRequest request = new PhotoColorizerToBase64ByUrlRequest();
        request.setUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        PhotoColorizerToBase64ByUrlResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoColorizerToBase64ByFile() throws Exception {
        PhotoColorizerToBase64ByFileRequest request = new PhotoColorizerToBase64ByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\1.jpg"));
        PhotoColorizerToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoColorizerToByteByFile() throws Exception {
        PhotoColorizerToByteByFileRequest request = new PhotoColorizerToByteByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\1.jpg"));
        PhotoColorizerToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void faceCutoutToBase64ByUrl() throws Exception {
        FaceCutoutToBase64ByUrlRequest request = new FaceCutoutToBase64ByUrlRequest();
        request.setBgcolor("ffccff");
        request.setUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        FaceCutoutToBase64ByUrlResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void faceCutoutToBase64ByFile() throws Exception {
        FaceCutoutToBase64ByFileRequest request = new FaceCutoutToBase64ByFileRequest();
        request.setBgcolor("ffccff");
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        FaceCutoutToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void faceCutoutToByteByFile() throws Exception {
        FaceCutoutToByteByFileRequest request = new FaceCutoutToByteByFileRequest();
        request.setBgcolor("ffccff");
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        FaceCutoutToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void photoEnhancerToBase64ByUrl() throws Exception {
        PhotoEnhancerToBase64ByUrlRequest request = new PhotoEnhancerToBase64ByUrlRequest();
        request.setUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        PhotoEnhancerToBase64ByUrlResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoEnhancerToBase64ByFile() throws Exception {
        PhotoEnhancerToBase64ByFileRequest request = new PhotoEnhancerToBase64ByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\test\\blurry.jpg"));
        PhotoEnhancerToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoEnhancerToByteByFile() throws Exception {
        PhotoEnhancerToByteByFileRequest request = new PhotoEnhancerToByteByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\test\\blurry.jpg"));
        PhotoEnhancerToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void removeBackgroundToBase64ByUrl() throws Exception {
        RemoveBackgroundToBase64ByUrlRequest request = new RemoveBackgroundToBase64ByUrlRequest();
        request.setUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        RemoveBackgroundToBase64ByUrlResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void removeBackgroundToBase64ByFile() throws Exception {
        RemoveBackgroundToBase64ByFileRequest request = new RemoveBackgroundToBase64ByFileRequest();
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        RemoveBackgroundToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void removeBackgroundToByteByFile() throws Exception {
        RemoveBackgroundToByteByFileRequest request = new RemoveBackgroundToByteByFileRequest();
        request.setBgcolor("ffccff");
        request.setFile(new File("C:\\Users\\root\\Desktop\\aaaa.jpg"));
        RemoveBackgroundToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void write(byte[] bytes, String type) {
        String outputPath = "C:\\Users\\root\\Desktop\\test\\";
        String path = outputPath + UUID.randomUUID().toString().replaceAll("-", "") + "." + type;
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String toBASE64(InputStream inputStream) throws IOException {
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
        String formatName = getImgFormatName(imageInputStream);
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, formatName, out);
        byte[] bytes = Base64.getEncoder().encode(out.toByteArray());
        return new String(bytes);

    }

    public static String getImgFormatName(ImageInputStream inputStream) throws IOException {
        Iterator<ImageReader> iterator = ImageIO.getImageReaders(inputStream);
        if (iterator.hasNext()) {
            return iterator.next().getFormatName().toLowerCase();
        }
        return null;
    }


}
