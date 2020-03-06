package pub.cwb.workflow.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author athena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBase {
    private int innerId;

    private String name;

    private String cname;

    private String email;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
