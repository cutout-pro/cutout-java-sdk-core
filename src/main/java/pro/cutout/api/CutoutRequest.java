package pro.cutout.api;

import org.apache.http.entity.ContentType;

public interface CutoutRequest<T extends CutoutResponse> {

    String getApiUrl();

    String getHttpMethod();

    Class<T> getResponseClass();

    ContentType getContentType();
}
