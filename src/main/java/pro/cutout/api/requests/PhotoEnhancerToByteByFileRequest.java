package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.PhotoEnhancerToByteByFileResponse;

import java.io.File;

public class PhotoEnhancerToByteByFileRequest implements CutoutRequest<PhotoEnhancerToByteByFileResponse> {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String getApiUrl() {
        return "/matting?mattingType=18";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<PhotoEnhancerToByteByFileResponse> getResponseClass() {
        return PhotoEnhancerToByteByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
