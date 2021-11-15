package pro.cutout.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;


public class CutoutClient {

    private String apikey;
    private String serverUrl;

    public CutoutClient(String serverUrl, String apiKey) {
        this.serverUrl = serverUrl;
        this.apikey = apiKey;
    }

    /**
     * 执行隐私API请求。
     *
     * @param <T>     具体的API响应类
     * @param request 具体的API请求类
     * @return 具体的API响应
     */
    public <T extends CutoutResponse> T execute(CutoutRequest<T> request) throws Exception {
        HttpUriRequest httpUriRequest = getHttpRequest(request);
        httpUriRequest.setHeader("APIKEY", apikey);

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
            if (httpEntity.getContentType().getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
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

    public HttpUriRequest getHttpRequest(CutoutRequest cutoutRequest) throws Exception {
        String apiUrl = this.serverUrl + cutoutRequest.getApiUrl();
        ContentType contentType = cutoutRequest.getContentType();
        if (HttpGet.METHOD_NAME.equals(cutoutRequest.getHttpMethod())) {
            StringBuffer url = new StringBuffer(apiUrl);
            Field[] fields = cutoutRequest.getClass().getDeclaredFields();
            boolean first = !apiUrl.contains("?");
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(cutoutRequest);
                if (value == null) {
                    continue;
                }
                if (first) {
                    first = false;
                    url.append("?");
                } else {
                    url.append("&");
                }
                ApiBodyField bodyField = field.getAnnotation(ApiBodyField.class);
                if (bodyField == null) {
                    url.append(field.getName());
                    url.append("=");
                    url.append(value);
                    continue;
                }

                String fieldName = bodyField.fieldName();
                String name = fieldName != null && !fieldName.isEmpty() ? fieldName : field.getName();
                url.append(name);
                url.append("=");
                url.append(value);
            }
            HttpGet httpGet = new HttpGet(url.toString());
            return httpGet;
        } else {
            HttpPost httpPost = new HttpPost(apiUrl);
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
                    ApiBodyField bodyField = field.getAnnotation(ApiBodyField.class);
                    String name = field.getName();
                    Object value = field.get(cutoutRequest);

                    if (bodyField != null) {
                        String fieldName = bodyField.fieldName();
                        name = fieldName != null && !fieldName.isEmpty() ? bodyField.fieldName() : name;
                        if (value == null) {
                            value = bodyField.value();
                        }
                    }
                    if (value == null) {
                        continue;
                    }

                    if (value instanceof InputStream) {
                        multipartEntityBuilder.addBinaryBody(name, (InputStream) value);
                    } else if (value instanceof File) {
                        multipartEntityBuilder.addBinaryBody(name, (File) value);
                    } else if (value instanceof byte[]) {
                        multipartEntityBuilder.addBinaryBody(name, (byte[]) value);
                    } else {
                        multipartEntityBuilder.addTextBody(name, value.toString());
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
