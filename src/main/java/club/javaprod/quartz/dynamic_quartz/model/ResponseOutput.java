package club.javaprod.quartz.dynamic_quartz.model;

public class ResponseOutput {
    private int code;
    private String msg;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponseOutput(String msg) {
        this.msg = msg;
    }

    public ResponseOutput() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
