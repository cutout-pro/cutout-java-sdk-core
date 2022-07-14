package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.BaseCutoutRequest;
import pro.cutout.api.responses.PhotoEnhancerToBase64ByFileResponse;

import java.io.InputStream;

public class PhotoEnhancerToBase64ByFileRequest extends BaseCutoutRequest<PhotoEnhancerToBase64ByFileResponse> {
    private InputStream file;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    @Override
    public String getApiUrl() {
        return "/matting2?mattingType=18";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<PhotoEnhancerToBase64ByFileResponse> getResponseClass() {
        return PhotoEnhancerToBase64ByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
