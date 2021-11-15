package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.FetchCreditBalanceResponse;

public class FetchCreditBalanceRequest implements CutoutRequest<FetchCreditBalanceResponse> {
    @Override
    public String getApiUrl() {
        return "/mySubscription";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<FetchCreditBalanceResponse> getResponseClass() {
        return FetchCreditBalanceResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.APPLICATION_JSON;
    }
}
