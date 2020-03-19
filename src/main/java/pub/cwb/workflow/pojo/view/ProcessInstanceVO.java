package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Date;
import java.util.Map;

/**
 * @author athena
 */
@Data
public class ProcessInstanceVO {
    private String id;

    private boolean ended;

    private String activityId;

    private String processInstanceId;

    private String parentId;

    private String superExecutionId;

    private String rootProcessInstanceId;

    private String processDefinitionId;

    private String processDefinitionName;

    private String processDefinitionKey;

    private Integer processDefinitionVersion;

    private String deploymentId;

    private String businessKey;

    private boolean suspended;

    private Map<String, Object> processVariables;

    private String tenantId;

    private String name;

    private String description;

    private Date startTime;

    private String startUserId;

    public ProcessInstanceVO(ProcessInstance instance) {
        this.id = instance.getId();
        this.ended = instance.isEnded();
        this.activityId = instance.getActivityId();
        this.processInstanceId = instance.getProcessInstanceId();
        this.parentId = instance.getParentId();
        this.superExecutionId = instance.getSuperExecutionId();
        this.rootProcessInstanceId = instance.getRootProcessInstanceId();

        this.processDefinitionId =  instance.getProcessDefinitionId();

        this.processDefinitionName = instance.getProcessDefinitionName();

        this.processDefinitionKey = instance.getProcessDefinitionKey();

        this.processDefinitionVersion = instance.getProcessDefinitionVersion();

        this.deploymentId = instance.getDeploymentId();

        this.businessKey = instance.getBusinessKey();

        this.suspended = instance.isSuspended();

        this.processVariables = instance.getProcessVariables();

        this.tenantId = instance.getTenantId();

        this.name =  instance.getName();

        this.description = instance.getDescription();

        this.startTime = instance.getStartTime();

        this.startUserId = instance.getStartUserId();
    }
}
