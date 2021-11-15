package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.CartoonSelfieToBase64ByFileResponse;
import pro.cutout.api.responses.CartoonSelfieToByteByFileResponse;

import java.io.File;

public class CartoonSelfieToBase64ByFileRequest implements CutoutRequest<CartoonSelfieToBase64ByFileResponse> {

    private File file;
    private int cartoonType;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getCartoonType() {
        return cartoonType;
    }

    public void setCartoonType(int cartoonType) {
        this.cartoonType = cartoonType;
    }

    @Override
    public String getApiUrl() {
        return "/cartoonSelfie2";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
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
