package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Date;
import java.util.Map;

@Data
public class ProcessInstanceVO {
    private String ProcessDefinitionId;

    private String ProcessDefinitionName;

    private String ProcessDefinitionKey;

    private Integer ProcessDefinitionVersion;

    private String DeploymentId;

    private String BusinessKey;

    private boolean isSuspended;

    private Map<String, Object> ProcessVariables;

    private String TenantId;

    private String Name;

    private String Description;

    private Date StartTime;

    private String StartUserId;

    ProcessInstanceVO() {}

    public ProcessInstanceVO(ProcessInstance instance) {
        this.ProcessDefinitionId =  instance.getProcessDefinitionId();

        this.ProcessDefinitionName = instance.getProcessDefinitionName();

        this.ProcessDefinitionKey = instance.getProcessDefinitionKey();

        this.ProcessDefinitionVersion = instance.getProcessDefinitionVersion();

        this.DeploymentId = instance.getDeploymentId();

        this.BusinessKey = instance.getBusinessKey();

        this.isSuspended = instance.isSuspended();

        this.ProcessVariables = instance.getProcessVariables();

        this.TenantId = instance.getTenantId();

        this.Name =  instance.getName();

        this.Description = instance.getDescription();

        this.StartTime = instance.getStartTime();

        this.StartUserId = instance.getStartUserId();
    }
}
