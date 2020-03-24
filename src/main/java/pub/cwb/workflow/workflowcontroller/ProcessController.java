package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.req.BaseReq;
import pub.cwb.workflow.pojo.view.ProcessInstanceVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.*;

/**
 * @author athena
 */
@Api(tags = "Process Controller")
@RestController
@RequestMapping("pub/wf/process")
public class ProcessController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @GetMapping("/deploy")
    @ApiOperation(value = "Deploy process.", notes = "部署流程")
    public ResponseBase deployment(@RequestParam(required = false) String processDefId) {
        String path = "process/" + processDefId + ".bpmn20.xml";

        Deployment deployment;
        try{
            deployment = FlowableEngine.getEngine().getRepositoryService().createDeployment()
                    .name("test")
                    .addClasspathResource(path)
                    .deploy();
            logger.info("Deploy ID : " + deployment.getId());
        } catch (Exception e) {
            return new ResponseBase<>("500", e.getMessage());
        }

        return new ResponseBase<>("200", "Deploy ID : " + deployment.getId());
    }

    @GetMapping("/deployquery")
    @ApiOperation(value = "Process Deployment Query.", notes = "查询已部署流程")
    public ResponseBase deployquery(@RequestParam(required = true) String proKey) {
        ResponseBase<List<Map>> re = new ResponseBase<>();
        // param check
        if(StringUtils.isBlank(proKey)) {
            re.setMsg("Process key is not valid : " + (proKey == null ? "" : proKey));
            re.setCode("500");
            return re;
        }
        // deploy query
        List<Deployment> deployments;
        try{
            deployments = FlowableEngine.getEngine().getRepositoryService()
                    .createDeploymentQuery().processDefinitionKey(proKey).list();
        } catch (Exception e) {
            re.setMsg(e.getMessage());
            re.setCode("500");
            return re;
        }
        // data process
        List<Map> datas = new LinkedList<>();
        for (Deployment deployment: deployments
             ) {
            Map tmp = new HashMap();
            tmp.put("ID", deployment.getId());
            tmp.put("KEY", deployment.getKey());
            tmp.put("TenantId", deployment.getTenantId());
            tmp.put("Name", deployment.getName());
            tmp.put("Time", deployment.getDeploymentTime());
            //tmp.put("Resource", deployment.getResources());
            datas.add(tmp);
        }
        re.setData(datas);
        return re;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Create a Processes Instance.", notes = "创建一个流程实例")
    public ResponseBase createProcessInstance(@RequestBody BaseReq req) {
        ResponseBase<ProcessInstanceVO> re = new ResponseBase<>();

        if (StringUtils.isBlank(req.getProcessDefKey()) || req.getGlobalVars() == null) {
            re.setCode("500");
            re.setMsg("param is not valid.");
            return re;
        }

        List<Deployment> deployments = FlowableEngine.getEngine()
                .getRepositoryService()
                .createDeploymentQuery()
                .processDefinitionKey(req.getProcessDefKey())
                .list();

        if (deployments == null || deployments.size() == 0){
            re.setMsg("没有该流程,流程尚未创建");
            re.setCode("500");
            return  re;
        }

        ProcessInstance instance = FlowableEngine.getEngine()
                .getRuntimeService()
                .startProcessInstanceByKey(req.getProcessDefKey(), req.getGlobalVars());

        re.setData(new ProcessInstanceVO(instance));

        return re;
    }

}
