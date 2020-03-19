package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.task.api.Task;

import java.util.Date;
import java.util.Map;

/**
 * @author athena
 */
@Data
public class TaskVO {
    private String id;

    private String name;

    private String description;

    private int priority;

    private String Owner;

    private String Assignee;

    private String ProcessInstanceId;

    private String ExecutionId;

    private String TaskDefinitionId;

    private String ProcessDefinitionId;

    private String ScopeId;

    private String SubScopeId;

    private String ScopeType;

    private String ScopeDefinitionId;

    private String PropagatedStageInstanceId;

    private Date CreateTime;

    private String TaskDefinitionKey;

    private Date DueDate;

    private String Category;

    private String ParentTaskId;

    private String TenantId;

    private String FormKey;

    private Map<String, Object> TaskLocalVariables;

    private Map<String, Object> ProcessVariables;

    private Date ClaimTime;
    
    public TaskVO(Task task) {
        this.id = task.getId();

        this.name = task.getName();

        this.description = task.getDescription();

        this.priority =  task.getPriority();

        this.Owner = task.getOwner();

        this.Assignee = task.getAssignee();

        this.ProcessInstanceId = task.getProcessInstanceId();

        this.ExecutionId = task.getExecutionId();

        this.TaskDefinitionId = task.getTaskDefinitionId();

        this.ProcessDefinitionId = task.getProcessDefinitionId();

        this.ScopeId = task.getScopeId();

        this.SubScopeId = task.getSubScopeId();

        this.ScopeType = task.getScopeType();

        this.ScopeDefinitionId = task.getScopeDefinitionId();

        this.PropagatedStageInstanceId = task.getPropagatedStageInstanceId();

        this.CreateTime = task.getCreateTime();

        this.TaskDefinitionKey = task.getTaskDefinitionKey();

        this.DueDate = task.getDueDate();

        this.Category = task.getCategory();

        this.ParentTaskId = task.getParentTaskId();

        this.TenantId = task.getTenantId();

        this.FormKey = task.getFormKey();

        this.TaskLocalVariables =  task.getTaskLocalVariables();

        this.ProcessVariables = task.getProcessVariables();

        this.ClaimTime = task.getClaimTime();
    }
}
