package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.PhotoEnhancerToBase64ByFileResponse;
import pro.cutout.api.responses.PhotoEnhancerToBase64ByUrlResponse;

import java.io.File;

public class PhotoEnhancerToBase64ByUrlRequest implements CutoutRequest<PhotoEnhancerToBase64ByUrlResponse> {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getApiUrl() {
        return "/mattingByUrl?mattingType=18";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<PhotoEnhancerToBase64ByUrlResponse> getResponseClass() {
        return PhotoEnhancerToBase64ByUrlResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
