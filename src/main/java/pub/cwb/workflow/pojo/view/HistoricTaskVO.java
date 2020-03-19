package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.task.api.history.HistoricTaskInstance;

import java.util.Date;
import java.util.Map;

/**
 * @author athena
 */
@Data
public class HistoricTaskVO {
    private String id;

    private String name;

    private String description;

    private int priority;

    private String owner;

    private String assignee;

    private String processInstanceId;

    private String executionId;

    private String taskDefinitionId;

    private String processDefinitionId;

    private String scopeId;

    private String subScopeId;

    private String scopeType;

    private String scopeDefinitionId;

    private String propagatedStageInstanceId;

    private Date createTime;

    private String taskDefinitionKey;

    private Date dueDate;

    private String category;

    private String parentTaskId;

    private String tenantId;

    private String formKey;

    private Map<String, Object> taskLocalVariables;

    private Map<String, Object> processVariables;

    private Date claimTime;

    public HistoricTaskVO(HistoricTaskInstance task) {
        this.id = task.getId();

        this.name = task.getName();

        this.description = task.getDescription();

        this.priority =  task.getPriority();

        this.owner = task.getOwner();

        this.assignee = task.getAssignee();

        this.processInstanceId = task.getProcessInstanceId();

        this.executionId = task.getExecutionId();

        this.taskDefinitionId = task.getTaskDefinitionId();

        this.processDefinitionId = task.getProcessDefinitionId();

        this.scopeId = task.getScopeId();

        this.subScopeId = task.getSubScopeId();

        this.scopeType = task.getScopeType();

        this.scopeDefinitionId = task.getScopeDefinitionId();

        this.propagatedStageInstanceId = task.getPropagatedStageInstanceId();

        this.createTime = task.getCreateTime();

        this.taskDefinitionKey = task.getTaskDefinitionKey();

        this.dueDate = task.getDueDate();

        this.category = task.getCategory();

        this.parentTaskId = task.getParentTaskId();

        this.tenantId = task.getTenantId();

        this.formKey = task.getFormKey();

        this.taskLocalVariables =  task.getTaskLocalVariables();

        this.processVariables = task.getProcessVariables();

        this.claimTime = task.getClaimTime();
    }
}
