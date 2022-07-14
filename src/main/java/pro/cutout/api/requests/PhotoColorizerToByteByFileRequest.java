package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.BaseCutoutRequest;
import pro.cutout.api.responses.PhotoColorizerToByteByFileResponse;

import java.io.InputStream;

public class PhotoColorizerToByteByFileRequest extends BaseCutoutRequest<PhotoColorizerToByteByFileResponse> {
    private InputStream file;

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    @Override
    public String getApiUrl() {
        return "/matting?mattingType=19";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<PhotoColorizerToByteByFileResponse> getResponseClass() {
        return PhotoColorizerToByteByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
