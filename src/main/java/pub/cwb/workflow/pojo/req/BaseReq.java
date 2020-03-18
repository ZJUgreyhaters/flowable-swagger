package pub.cwb.workflow.pojo.req;

import lombok.Data;

import java.util.Map;

@Data
public class BaseReq {
    private Map<String, Object> globalVars;
    private Map<String, Object> localVars;

    private String processDefKey;
}
