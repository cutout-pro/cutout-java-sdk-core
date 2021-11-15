package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.PhotoAnimerTaskCreateResponse;

public class PhotoAnimerTaskCreateRequest implements CutoutRequest<PhotoAnimerTaskCreateResponse> {

    private String imageUrl;
    private Integer templateId;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    @Override
    public String getApiUrl() {
        return "/faceDriven/submitTaskByUrl";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<PhotoAnimerTaskCreateResponse> getResponseClass() {
        return PhotoAnimerTaskCreateResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
