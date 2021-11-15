package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.PhotoAnimerTaskCreateResponse;
import pro.cutout.api.responses.PhotoAnimerTaskGetResponse;

public class PhotoAnimerTaskGetRequest implements CutoutRequest<PhotoAnimerTaskGetResponse> {

    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getApiUrl() {
        return "/faceDriven/getTaskInfo";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<PhotoAnimerTaskGetResponse> getResponseClass() {
        return PhotoAnimerTaskGetResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
