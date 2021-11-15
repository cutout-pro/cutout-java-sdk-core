package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;

public class PassportPhotoResponse extends CutoutResponse<PassportPhotoResponse.Data> {

    public static class Data {
        private String idPhotoImage;
        private String printLayoutImage;
        private Integer idPhotoCount;

        public String getIdPhotoImage() {
            return idPhotoImage;
        }

        public void setIdPhotoImage(String idPhotoImage) {
            this.idPhotoImage = idPhotoImage;
        }

        public String getPrintLayoutImage() {
            return printLayoutImage;
        }

        public void setPrintLayoutImage(String printLayoutImage) {
            this.printLayoutImage = printLayoutImage;
        }

        public Integer getIdPhotoCount() {
            return idPhotoCount;
        }

        public void setIdPhotoCount(Integer idPhotoCount) {
            this.idPhotoCount = idPhotoCount;
        }
    }
}
