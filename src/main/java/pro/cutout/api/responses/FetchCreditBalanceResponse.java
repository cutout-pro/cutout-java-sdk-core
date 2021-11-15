package pro.cutout.api.responses;

import pro.cutout.api.CutoutResponse;

import java.math.BigDecimal;
import java.util.Date;

public class FetchCreditBalanceResponse extends CutoutResponse<FetchCreditBalanceResponse.Data> {
    public static class Data {
        //Remaining amount of monthly subscription
        private BigDecimal monthBalance;
        //Remaining credits for pay-as-you-go plans
        private BigDecimal imageBalance;
        //Remaining time for video background removal
        private BigDecimal videoBalance;
        //Monthly subscription start date
        private Date monthStartDate;
        //Monthly subscription expiration date
        private Date monthExpireDate;

        public BigDecimal getMonthBalance() {
            return monthBalance;
        }

        public void setMonthBalance(BigDecimal monthBalance) {
            this.monthBalance = monthBalance;
        }

        public BigDecimal getImageBalance() {
            return imageBalance;
        }

        public void setImageBalance(BigDecimal imageBalance) {
            this.imageBalance = imageBalance;
        }

        public BigDecimal getVideoBalance() {
            return videoBalance;
        }

        public void setVideoBalance(BigDecimal videoBalance) {
            this.videoBalance = videoBalance;
        }

        public Date getMonthStartDate() {
            return monthStartDate;
        }

        public void setMonthStartDate(Date monthStartDate) {
            this.monthStartDate = monthStartDate;
        }

        public Date getMonthExpireDate() {
            return monthExpireDate;
        }

        public void setMonthExpireDate(Date monthExpireDate) {
            this.monthExpireDate = monthExpireDate;
        }
    }
}
