package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.engine.history.HistoricProcessInstance;

import java.util.Date;
import java.util.Map;

@Data
public class ProcessInstanceHisVO {
    private String Id;

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

    private Date EndTime;

    private String StartUserId;

    public ProcessInstanceHisVO(HistoricProcessInstance instance) {
        this.Id = instance.getId();

        this.ProcessDefinitionId =  instance.getProcessDefinitionId();

        this.ProcessDefinitionName = instance.getProcessDefinitionName();

        this.ProcessDefinitionKey = instance.getProcessDefinitionKey();

        this.ProcessDefinitionVersion = instance.getProcessDefinitionVersion();

        this.DeploymentId = instance.getDeploymentId();

        this.BusinessKey = instance.getBusinessKey();

        this.ProcessVariables = instance.getProcessVariables();

        this.TenantId = instance.getTenantId();

        this.Name =  instance.getName();

        this.Description = instance.getDescription();

        this.StartTime = instance.getStartTime();

        this.EndTime = instance.getEndTime();

        this.StartUserId = instance.getStartUserId();
    }
}
