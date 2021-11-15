package pro.cutout.api.requests;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import pro.cutout.api.CutoutRequest;
import pro.cutout.api.responses.ImageRetouchResponse;

import java.util.List;

public class ImageRetouchRequest implements CutoutRequest<ImageRetouchResponse> {
    private String base64;
    private List<Rectangle> rectangles;
    private String maskBase64;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public String getMaskBase64() {
        return maskBase64;
    }

    public void setMaskBase64(String maskBase64) {
        this.maskBase64 = maskBase64;
    }

    public static class Rectangle{
        private int height;
        private int width;
        private int x;
        private int y;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    @Override
    public String getApiUrl() {
        return "/imageFix";
    }

    @Override
    public String getHttpMethod() {
        return HttpPost.METHOD_NAME;
    }

    @Override
    public Class<ImageRetouchResponse> getResponseClass() {
        return ImageRetouchResponse.class;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.APPLICATION_JSON;
    }
}
