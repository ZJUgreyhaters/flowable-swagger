package pub.cwb.workflow.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pub.cwb.workflow.pojo.BaseReq;
import pub.cwb.workflow.pojo.VarsQryReq;
import pub.cwb.workflow.pojo.view.ProcessInstanceVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author athena
 */
@Service
public class RunTimeService {
    private static final Logger logger = LoggerFactory.getLogger(RunTimeService.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    /**
     * 获取流程变量/任务变量
     * @param req
     * @return
     */
    public Map getVars(VarsQryReq req) {
        logger.info("获取变量: " + req.toString());
        if (StringUtils.isBlank(req.getExecutionId()) && StringUtils.isBlank(req.getTaskId()) ) {
            throw new RuntimeException("参数错误");
        }

        if (StringUtils.isNotBlank(req.getTaskId())) {
            return FlowableEngine.getEngine().getTaskService().getVariables(req.getTaskId());
        } else {
            return FlowableEngine.getEngine().getRuntimeService().getVariables(req.getExecutionId());
        }

    }

    /**
     * 创建流程实例
     * @param req
     * @return
     */
    public ProcessInstanceVO createProcess(BaseReq req) {

        List<Deployment> deployments = FlowableEngine.getEngine()
                .getRepositoryService()
                .createDeploymentQuery()
                .processDefinitionKey(defKey)
                .list();

        if (deployments == null || deployments.size() == 0){
            throw new RuntimeException("没有该流程,流程尚未创建");
        }

        ProcessInstance instance = FlowableEngine.getEngine()
                .getRuntimeService()
                .startProcessInstanceByKey(defKey, req.getGlobalVars());

        return new ProcessInstanceVO(instance);
    }

    /**
     * 获取流程实例
     * @return
     */
    public List<? extends Object> getProcessInstance() {

        List<ProcessInstance> processInstances = FlowableEngine.getEngine()
                .getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(defKey)
                .orderByProcessDefinitionId().desc()
                .list();

        List<ProcessInstanceVO> datas = new LinkedList<>();

        for (ProcessInstance p : processInstances
        ) {
            ProcessInstanceVO tmp = new ProcessInstanceVO(p);

            datas.add(tmp);
        }

        return datas;
    }
}
