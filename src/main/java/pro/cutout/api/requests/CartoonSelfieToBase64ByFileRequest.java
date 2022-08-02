package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.BaseCutoutRequest;
import pro.cutout.api.responses.CartoonSelfieToBase64ByFileResponse;

import java.io.InputStream;

public class CartoonSelfieToBase64ByFileRequest extends BaseCutoutRequest<CartoonSelfieToBase64ByFileResponse> {

    private InputStream file;
    private int cartoonType;
    private String outputFormat;
    private Integer outSize;

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public Integer getOutSize() {
        return outSize;
    }

    public void setOutSize(Integer outSize) {
        this.outSize = outSize;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
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
