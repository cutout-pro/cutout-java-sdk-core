package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.FaceCutoutToBase64ByFileResponse;
import pro.cutout.api.responses.FaceCutoutToByteByFileResponse;

import java.io.File;

public class FaceCutoutToBase64ByFileRequest implements CutoutRequest<FaceCutoutToBase64ByFileResponse> {
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
        return "/matting2?mattingType=3";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<FaceCutoutToBase64ByFileResponse> getResponseClass() {
        return FaceCutoutToBase64ByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
