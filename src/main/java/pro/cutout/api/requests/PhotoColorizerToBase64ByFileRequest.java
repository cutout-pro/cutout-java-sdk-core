package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.PhotoColorizerToBase64ByFileResponse;
import pro.cutout.api.responses.PhotoColorizerToByteByFileResponse;

import java.io.File;

public class PhotoColorizerToBase64ByFileRequest implements CutoutRequest<PhotoColorizerToBase64ByFileResponse> {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String getApiUrl() {
        return "/matting2?mattingType=19";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<PhotoColorizerToBase64ByFileResponse> getResponseClass() {
        return PhotoColorizerToBase64ByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
