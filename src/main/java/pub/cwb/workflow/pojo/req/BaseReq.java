package pub.cwb.workflow.pojo.req;

import lombok.Data;

import java.util.Map;

/**
 * @author athena
 */
@Data
public class BaseReq {
    private Map<String, Object> globalVars;
    private Map<String, Object> localVars;

    private Map<String,Object> optInfo;

    private String processDefKey;

    private String userId;


}
