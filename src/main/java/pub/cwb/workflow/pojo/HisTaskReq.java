package pub.cwb.workflow.pojo;

import lombok.Data;

@Data
public class HisTaskReq {

    private String userId;

    private String isAssigned = "0";

    private String finished = "0";
}
