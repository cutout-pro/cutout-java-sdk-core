package pro.cutout.api;

public abstract class BaseCutoutRequest<T extends CutoutResponse> implements CutoutRequest<T> {
    private String sign;
    private Long signExpireTime;
    private Long uid;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getSignExpireTime() {
        return signExpireTime;
    }

    public void setSignExpireTime(Long signExpireTime) {
        this.signExpireTime = signExpireTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
