package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.FaceCutoutToByteByFileResponse;
import pro.cutout.api.responses.PhotoColorizerToByteByFileResponse;

import java.io.File;

public class PhotoColorizerToByteByFileRequest implements CutoutRequest<PhotoColorizerToByteByFileResponse> {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
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
