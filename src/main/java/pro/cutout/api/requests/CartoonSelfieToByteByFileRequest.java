package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.CartoonSelfieToByteByFileResponse;

import java.io.File;

public class CartoonSelfieToByteByFileRequest implements CutoutRequest<CartoonSelfieToByteByFileResponse> {

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
        return "/cartoonSelfie";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<CartoonSelfieToByteByFileResponse> getResponseClass() {
        return CartoonSelfieToByteByFileResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.MULTIPART_FORM_DATA;
    }
}
