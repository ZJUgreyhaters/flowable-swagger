package pub.cwb.workflow.pojo.view;

import lombok.Data;
import org.flowable.engine.history.HistoricProcessInstance;

import java.util.Date;
import java.util.Map;

/**
 * @author athena
 */
@Data
public class ProcessInstanceHisVO {
    private String id;

    private String processDefinitionId;

    private String processDefinitionName;

    private String processDefinitionKey;

    private Integer processDefinitionVersion;

    private String deploymentId;

    private String businessKey;

    private Map<String, Object> processVariables;

    private String tenantId;

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private String startUserId;

    public ProcessInstanceHisVO(HistoricProcessInstance instance) {
        this.id = instance.getId();

        this.processDefinitionId =  instance.getProcessDefinitionId();

        this.processDefinitionName = instance.getProcessDefinitionName();

        this.processDefinitionKey = instance.getProcessDefinitionKey();

        this.processDefinitionVersion = instance.getProcessDefinitionVersion();

        this.deploymentId = instance.getDeploymentId();

        this.businessKey = instance.getBusinessKey();

        this.processVariables = instance.getProcessVariables();

        this.tenantId = instance.getTenantId();

        this.name =  instance.getName();

        this.description = instance.getDescription();

        this.startTime = instance.getStartTime();

        this.endTime = instance.getEndTime();

        this.startUserId = instance.getStartUserId();
    }
}
