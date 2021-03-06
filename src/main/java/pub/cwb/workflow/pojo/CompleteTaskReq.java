package pub.cwb.workflow.pojo;

import lombok.Data;

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
