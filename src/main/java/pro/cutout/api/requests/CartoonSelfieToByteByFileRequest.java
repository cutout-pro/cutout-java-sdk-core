package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.BaseCutoutRequest;
import pro.cutout.api.responses.CartoonSelfieToByteByFileResponse;

import java.io.InputStream;

public class CartoonSelfieToByteByFileRequest extends BaseCutoutRequest<CartoonSelfieToByteByFileResponse> {

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
