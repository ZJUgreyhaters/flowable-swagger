package pub.cwb.workflow.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.cwb.workflow.constants.ReturnCode;

import java.util.List;

@Data
public class ResponseBase<T> {
    private T data;
    private String msg;
    private String code;

    public ResponseBase() {
        this.msg = "";
        this.code = ReturnCode.SUCCESS.getCode();
    }

    public ResponseBase(T data){
        this();
        this.data = data;
    }

    public ResponseBase(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseBase(T data, String code, String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
