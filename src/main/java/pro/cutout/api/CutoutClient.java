package pro.cutout.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.UUID;


public class CutoutClient {

    private String apikey;
    private String serverUrl;

    public CutoutClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public CutoutClient(String serverUrl, String apiKey) {
        this.serverUrl = serverUrl;
        this.apikey = apiKey;
    }

    public <T extends CutoutResponse> T execute(BaseCutoutRequest<T> request) throws Exception {
        HttpUriRequest httpUriRequest = getHttpRequest(request);
        if (apikey != null) {
            httpUriRequest.setHeader("APIKEY", apikey);
        }

        T cutoutResponse;
        try {
            check(request);
        } catch (Exception e) {
            e.printStackTrace();
            cutoutResponse = request.getResponseClass().getDeclaredConstructor().newInstance();
            cutoutResponse.setCode(-1);
            cutoutResponse.setMsg(e.getMessage());
            cutoutResponse.setTime(System.currentTimeMillis());
            return cutoutResponse;
        }

        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(httpUriRequest)
        ) {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                cutoutResponse = request.getResponseClass().getDeclaredConstructor().newInstance();
                cutoutResponse.setCode(-1);
                cutoutResponse.setMsg("http status code:" + response.getStatusLine().getStatusCode());
                cutoutResponse.setTime(System.currentTimeMillis());
                return cutoutResponse;
            }
            HttpEntity httpEntity = response.getEntity();
            Header header = httpEntity.getContentType();
            if (header != null && header.getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
                String result = EntityUtils.toString(response.getEntity());
                cutoutResponse = JSON.parseObject(result, request.getResponseClass());
            } else {
                byte[] bytes = EntityUtils.toByteArray(response.getEntity());
                cutoutResponse = request.getResponseClass().getDeclaredConstructor().newInstance();
                cutoutResponse.setCode(0);
                cutoutResponse.setData(bytes);
                cutoutResponse.setTime(System.currentTimeMillis());
            }
            return cutoutResponse;
        } catch (Exception e) {
            e.printStackTrace();
            cutoutResponse = request.getResponseClass().getDeclaredConstructor().newInstance();
            cutoutResponse.setCode(-1);
            cutoutResponse.setMsg(e.getMessage());
            cutoutResponse.setTime(System.currentTimeMillis());
            return cutoutResponse;
        }
    }

    public HttpUriRequest getHttpRequest(BaseCutoutRequest cutoutRequest) throws Exception {
        URIBuilder newBuilder = new URIBuilder(this.serverUrl + cutoutRequest.getApiUrl());
        String sign = cutoutRequest.getSign();
        if (sign != null) {
            newBuilder.setParameter("sign", sign);
        }
        Long signExpireTime = cutoutRequest.getSignExpireTime();
        if (signExpireTime != null) {
            newBuilder.setParameter("signExpireTime", signExpireTime.toString());
        }
        Long uid = cutoutRequest.getUid();
        if (uid != null) {
            newBuilder.setParameter("uid", uid.toString());
        }
        ContentType contentType = cutoutRequest.getContentType();
        if (HttpGet.METHOD_NAME.equals(cutoutRequest.getHttpMethod())) {
            //获取所有字段,public和protected和private,但是不包括父类字段
            Field[] fields = cutoutRequest.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(cutoutRequest);

                ApiBodyField bodyField = field.getAnnotation(ApiBodyField.class);
                if (bodyField != null) {
                    String name = bodyField.fieldName();
                    if (name != null && !name.isEmpty()) {
                        fieldName = name;
                    }
                    if (value == null) {
                        value = bodyField.value();
                    }
                }
                if (value == null) {
                    continue;
                }
                newBuilder.setParameter(fieldName, value.toString());
                continue;
            }
            HttpGet httpGet = new HttpGet(newBuilder.build());
            return httpGet;
        } else {
            HttpPost httpPost = new HttpPost(newBuilder.build());
            if (ContentType.APPLICATION_JSON.equals(contentType)) {
                String content = JSONObject.toJSONString(cutoutRequest);
                StringEntity stringEntity = new StringEntity(content, "UTF-8");
                stringEntity.setContentType(ContentType.APPLICATION_JSON.toString());
                httpPost.setEntity(stringEntity);
            } else if (ContentType.MULTIPART_FORM_DATA.equals(contentType)) {
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                multipartEntityBuilder.setContentType(contentType);
                Field[] fields = cutoutRequest.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(cutoutRequest);

                    ApiBodyField bodyField = field.getAnnotation(ApiBodyField.class);
                    if (bodyField != null) {
                        String name = bodyField.fieldName();
                        if (name != null && !name.isEmpty()) {
                            fieldName = name;
                        }
                        if (value == null) {
                            value = bodyField.value();
                        }
                    }
                    if (value == null) {
                        continue;
                    }

                    if (value instanceof InputStream) {
                        multipartEntityBuilder.addBinaryBody(fieldName, (InputStream) value, ContentType.DEFAULT_BINARY, UUID.randomUUID().toString());
                    } else if (value instanceof File) {
                        multipartEntityBuilder.addBinaryBody(fieldName, (File) value);
                    } else if (value instanceof byte[]) {
                        multipartEntityBuilder.addBinaryBody(fieldName, (byte[]) value, ContentType.DEFAULT_BINARY, UUID.randomUUID().toString());
                    } else {
                        multipartEntityBuilder.addTextBody(fieldName, value.toString());
                    }
                }
                httpPost.setEntity(multipartEntityBuilder.build());
            }
            return httpPost;
        }
    }

    private <T extends CutoutResponse> void check(CutoutRequest<T> request) {

    }
}
