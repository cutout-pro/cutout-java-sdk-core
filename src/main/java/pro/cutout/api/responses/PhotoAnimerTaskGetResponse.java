package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;

public class PhotoAnimerTaskGetResponse extends CutoutResponse<PhotoAnimerTaskGetResponse.Data> {

    public static class Data{
        private Long taskId;
        private String resultUrl;
        /**
         * 0:IN_PROGRESS，1:COMPLETED 2:FAILED，
         */
        private Integer status;

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

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
