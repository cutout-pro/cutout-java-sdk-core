package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.RemoveBackgroundToBase64ByUrlResponse;
import pro.cutout.api.responses.RemoveBackgroundToByteByFileResponse;

public class RemoveBackgroundToBase64ByUrlRequest implements CutoutRequest<RemoveBackgroundToBase64ByUrlResponse> {

    private String url;
    private Boolean crop;
    private String bgcolor;
    private Boolean preview;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "/mattingByUrl?mattingType=6";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<RemoveBackgroundToBase64ByUrlResponse> getResponseClass() {
        return RemoveBackgroundToBase64ByUrlResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
