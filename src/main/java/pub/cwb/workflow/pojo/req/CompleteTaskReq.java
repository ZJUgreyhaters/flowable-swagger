package pub.cwb.workflow.pojo.req;

import lombok.Data;
import pub.cwb.workflow.pojo.BaseReq;

import javax.validation.constraints.NotBlank;

/**
 * @author athena
 */
@Data
public class CompleteTaskReq extends BaseReq {
    @NotBlank
    private String taskId;

    private String executionId;
}
