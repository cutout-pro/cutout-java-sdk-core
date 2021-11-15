package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.CartoonSelfieToBase64ByFileResponse;

import java.io.File;

public class CartoonSelfieToBase64ByUrlRequest implements CutoutRequest<CartoonSelfieToBase64ByFileResponse> {

    private String url;
    private int cartoonType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCartoonType() {
        return cartoonType;
    }

    public void setCartoonType(int cartoonType) {
        this.cartoonType = cartoonType;
    }

    @Override
    public String getApiUrl() {
        return "/cartoonSelfieByUrl";
    }

    @Override
    public String getHttpMethod() {
        return HttpGet.METHOD_NAME;
    }

    @Override
    public Class<CartoonSelfieToBase64ByFileResponse> getResponseClass() {
        return CartoonSelfieToBase64ByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
