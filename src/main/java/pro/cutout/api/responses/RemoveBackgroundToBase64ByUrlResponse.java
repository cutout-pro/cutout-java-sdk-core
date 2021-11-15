package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;


public class RemoveBackgroundToBase64ByUrlResponse extends CutoutResponse<RemoveBackgroundToBase64ByUrlResponse.Data> {
    public static class Data{
        private String imageBase64;

        public String getImageBase64() {
            return imageBase64;
        }

        public void setImageBase64(String imageBase64) {
            this.imageBase64 = imageBase64;
        }
    }
}
