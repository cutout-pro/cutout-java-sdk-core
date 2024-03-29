package pro.cutout.api;


import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pro.cutout.api.requests.*;
import pro.cutout.api.responses.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

class CutoutTests {
    static String serverUrl = "https://www.cutout.pro/api/v1";

    //visit https://www.cutout.pro/user/secret-key to get your api key
    static String apikey = "Your API KEY";
    static CutoutClient client = new CutoutClient(serverUrl, apikey);
    static Map<String, byte[]> cache = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("===============================================");
        photoAnimerTaskGet();
        System.out.println("===============================================");
    }

    public static void fetchCreditBalance() throws Exception {
        FetchCreditBalanceRequest request = new FetchCreditBalanceRequest();
        FetchCreditBalanceResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoAnimerTaskGet() throws Exception {
        Long taskId = photoAnimerTaskCreate();
        if(taskId==null){
            return;
        }
        for (int i = 0; i <= 100; i++) {
            PhotoAnimerTaskGetRequest request = new PhotoAnimerTaskGetRequest();
            request.setTaskId(taskId);
            PhotoAnimerTaskGetResponse response = client.execute(request);
            PhotoAnimerTaskGetResponse.Data date = response.getData();
            if (date.getStatus().equals(1)) {
                System.out.println(date.getResultUrl());
                byte[] bytes = download(date.getResultUrl());
                write(bytes, "mp4");
                return;
            } else if (date.getStatus().equals(2)) {
                System.out.println(JSON.toJSONString(response));
                return;
            }else{
                System.out.println("sleep 1 second");
                Thread.sleep(1000L);
            }
        }
    }

    public static Long photoAnimerTaskCreate() throws Exception {
        PhotoAnimerTaskCreateRequest request = new PhotoAnimerTaskCreateRequest();
        request.setImageUrl("https://img.alicdn.com/bao/uploaded/i1/919618044/O1CN01BY1v6929ICeBbILpK_!!919618044.jpg");
        request.setTemplateId(0);
        PhotoAnimerTaskCreateResponse response = client.execute(request);
        return response.getData();

    }

    public static void passportPhoto() throws Exception {
        String base64 = toBASE64(getResources("img/boy.jpg"));
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
        request.setFile(getResources("img/boy.jpg"));
        request.setCartoonType(3);
        CartoonSelfieToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void cartoonSelfieToByteByFile() throws Exception {
        CartoonSelfieToByteByFileRequest request = new CartoonSelfieToByteByFileRequest();
        request.setFile(getResources("img/boy.jpg"));
        request.setCartoonType(1);
        CartoonSelfieToByteByFileResponse response = client.execute(request);
        write((byte[]) response.getData(), "png");
    }

    public static void imageRetouch() throws Exception {
        String base64 = toBASE64(getResources("img/boy.jpg"));
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
        request.setFile(getResources("img/grayScale.jpg"));
        PhotoColorizerToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoColorizerToByteByFile() throws Exception {
        PhotoColorizerToByteByFileRequest request = new PhotoColorizerToByteByFileRequest();
        request.setFile(getResources("img/grayScale.jpg"));
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
        request.setFile(getResources("img/boy.jpg"));
        FaceCutoutToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void faceCutoutToByteByFile() throws Exception {
        FaceCutoutToByteByFileRequest request = new FaceCutoutToByteByFileRequest();
        request.setBgcolor("ffccff");
        request.setFile(getResources("img/boy.jpg"));
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
        request.setFile(getResources("img/boy.jpg"));
        PhotoEnhancerToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void photoEnhancerToByteByFile() throws Exception {
        PhotoEnhancerToByteByFileRequest request = new PhotoEnhancerToByteByFileRequest();
        request.setFile(getResources("img/boy.jpg"));
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
        request.setFile(getResources("img/boy.jpg"));
        RemoveBackgroundToBase64ByFileResponse response = client.execute(request);
        write(JSON.toJSONBytes(response), "json");
    }

    public static void removeBackgroundToByteByFile() throws Exception {
        RemoveBackgroundToByteByFileRequest request = new RemoveBackgroundToByteByFileRequest();
        request.setBgcolor("ffccff");
        request.setFile(getResources("img/boy.jpg"));
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

    public static byte[] download(String url) {
        HttpGet httpGet = new HttpGet(url);
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpGet)
        ) {
            byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toBASE64(InputStream inputStream) throws IOException {
        byte[] data = inputTobyte(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ImageInputStream imageInputStream = ImageIO.createImageInputStream(byteArrayInputStream);
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        String formatName = getImageType(data);
        ImageIO.write(bufferedImage, formatName, out);
        byte[] bytes = Base64.getEncoder().encode(out.toByteArray());
        return new String(bytes);
    }

    public static ByteArrayInputStream getResources(String name) {
        byte[] bytes = cache.computeIfAbsent(name, key -> {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
                byte[] buffer = new byte[1024];
                int i;
                while ((i = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, i);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                return null;
            }
        });
        return new ByteArrayInputStream(bytes);
    }
    public static byte[] inputTobyte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    public static String getImageType(byte[] data) {
        String type = null;
        if (data[1] == 'P' && data[2] == 'N' && data[3] == 'G') {
            type = "PNG";
            return type;
        }
        if (data[0] == 'G' && data[1] == 'I' && data[2] == 'F') {
            type = "GIF";
            return type;
        }
        if (data[6] == 'J' && data[7] == 'F' && data[8] == 'I'
                && data[9] == 'F') {
            type = "JPG";
            return type;
        }

        if (data[6] == 'E' && data[7] == 'x' && data[8] == 'i'
                && data[9] == 'f') {
            type = "JPG";
            return type;
        }
        if (data[8] == 'W' && data[9] == 'E' && data[10] == 'B'
                && data[11] == 'P') {
            type = "WEBP";
            return type;
        }

        if (data[0] == 'B' && data[1] == 'M' && data[2] == 'v'
                && data[3] == 'A') {
            type = "BMP";
            return type;
        }
        return type;
    }


}
