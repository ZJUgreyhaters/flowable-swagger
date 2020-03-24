package pub.cwb.workflow.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author athena
 */
@Data
public class AssigneTaskReq extends BaseReq {
    @NotBlank
    private String taskId;
}
