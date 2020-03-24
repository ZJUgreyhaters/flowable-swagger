package pub.cwb.workflow.service.impl;

import org.flowable.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author athena
 */
@Service
public class ReposityService {
    private static final Logger logger = LoggerFactory.getLogger(ReposityService.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    /**
     * 部署流程
     * @param processDefKey
     * @return Deployment
     */
    public Deployment deploymentProcess(String processDefKey) {
        String path = "process/" + processDefKey + ".bpmn20.xml";

        Deployment deployment;
        try{
            deployment = FlowableEngine.getEngine().getRepositoryService().createDeployment()
                    .name("test")
                    .addClasspathResource(path)
                    .deploy();
            logger.info("Deploy ID : " + deployment.getId());
        } catch (Exception e) {
            throw new RuntimeException("部署流程失败");
        }

        return deployment;
    }

    /**
     * 已部署流程查询
     * @param processDefKey
     * @return
     */
    public List<? extends Object> deploymentQuery(String processDefKey) {
        List<Deployment> deployments;
        try{
            deployments = FlowableEngine.getEngine().getRepositoryService()
                    .createDeploymentQuery().processDefinitionKey(processDefKey).list();
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }

        List<Map> datas = new LinkedList<>();
        for (Deployment deployment: deployments
        ) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("ID", deployment.getId());
            tmp.put("KEY", deployment.getKey());
            tmp.put("TenantId", deployment.getTenantId());
            tmp.put("Name", deployment.getName());
            tmp.put("Time", deployment.getDeploymentTime());
            datas.add(tmp);
        }
        return datas;
    }
}
