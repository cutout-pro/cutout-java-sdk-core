package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.BaseCutoutRequest;
import pro.cutout.api.responses.PassportPhotoResponse;

public class PassportPhotoRequest extends BaseCutoutRequest<PassportPhotoResponse> {
    private String base64;
    private String bgColor;
    private String bgColor2;
    private Integer dpi;
    private Integer mmHeight;
    private Integer mmWidth;
    private String printBgColor;
    private Integer printMmHeight;
    private Integer printMmWidth;
    private String dress;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getBgColor2() {
        return bgColor2;
    }

    public void setBgColor2(String bgColor2) {
        this.bgColor2 = bgColor2;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public Integer getMmHeight() {
        return mmHeight;
    }

    public void setMmHeight(Integer mmHeight) {
        this.mmHeight = mmHeight;
    }

    public Integer getMmWidth() {
        return mmWidth;
    }

    public void setMmWidth(Integer mmWidth) {
        this.mmWidth = mmWidth;
    }

    public String getPrintBgColor() {
        return printBgColor;
    }

    public void setPrintBgColor(String printBgColor) {
        this.printBgColor = printBgColor;
    }

    public Integer getPrintMmHeight() {
        return printMmHeight;
    }

    public void setPrintMmHeight(Integer printMmHeight) {
        this.printMmHeight = printMmHeight;
    }

    public Integer getPrintMmWidth() {
        return printMmWidth;
    }

    public void setPrintMmWidth(Integer printMmWidth) {
        this.printMmWidth = printMmWidth;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    @Override
    public String getApiUrl() {
        return "/idphoto/printLayout";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<PassportPhotoResponse> getResponseClass() {
        return PassportPhotoResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.APPLICATION_JSON;
    }
}
