package pub.cwb.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.service.impl.ProcessServiceImpl;
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

    @Autowired
    private ProcessServiceImpl processService;

    @GetMapping("/deploy")
    @ApiOperation(value = "Deploy process.", notes = "部署流程")
    public ResponseBase deployment(@RequestParam String processDefId) {
        Deployment deployment;
        try{
            deployment = FlowableEngine.getEngine().getRepositoryService().createDeployment()
                    .name("test")
                    .addClasspathResource("process/test1.bpmn20.xml")
                    .deploy();
            logger.info("Deploy ID : " + deployment.getId());
        } catch (Exception e) {
            return new ResponseBase<>("500", e.getMessage());
        }

        return new ResponseBase<>("200", "Deploy ID : " + deployment.getId());
    }

    @GetMapping("/deployquery")
    @ApiOperation(value = "Process Deployment Query.", notes = "查询已部署流程")
    public ResponseBase deployquery(@RequestParam String proKey) {
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

    @GetMapping("/create")
    @ApiOperation(value = "Create a Processes.", notes = "创建一个流程实例")
    public List listAll() {
        List<Process> re = new ArrayList<>();



        return re;
    }

}
