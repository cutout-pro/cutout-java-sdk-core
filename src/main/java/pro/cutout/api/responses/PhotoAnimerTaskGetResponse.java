package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;

public class PhotoAnimerTaskGetResponse extends CutoutResponse<PhotoAnimerTaskGetResponse.Data> {

    public static class Data{
        private Long taskId;
        private String resultUrl;

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public String getResultUrl() {
            return resultUrl;
        }

        public void setResultUrl(String resultUrl) {
            this.resultUrl = resultUrl;
        }
    }
}
