package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.RemoveBackgroundToByteByFileResponse;

import java.io.File;

public class RemoveBackgroundToByteByFileRequest implements CutoutRequest<RemoveBackgroundToByteByFileResponse> {

    private File file;
    private Boolean crop;
    private String bgcolor;
    private Boolean preview;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Boolean getCrop() {
        return crop;
    }

    public void setCrop(Boolean crop) {
        this.crop = crop;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public Boolean getPreview() {
        return preview;
    }

    public void setPreview(Boolean preview) {
        this.preview = preview;
    }

    @Override
    public String getApiUrl() {
        return "/matting?mattingType=6";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<RemoveBackgroundToByteByFileResponse> getResponseClass() {
        return RemoveBackgroundToByteByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
