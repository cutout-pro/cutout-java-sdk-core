package pro.cutout.api;


public class CutoutResponse<T> {

    //返回信息码
    private Integer code;
    //返回数据
    private T data;
    //返回信息内容
    private String msg;
    //响应的时间戳
    private Long time;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
