package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;

public class ImageRetouchResponse extends CutoutResponse<ImageRetouchResponse.Data> {

    public static class Data{
        private String status;
        private String imageUrl;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
