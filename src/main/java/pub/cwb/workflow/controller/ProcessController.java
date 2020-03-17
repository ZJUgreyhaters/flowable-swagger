package pub.cwb.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import java.util.ArrayList;
import java.util.List;

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
    @ApiOperation(value = "/deploy", notes = "部署流程")
    public String deployment(@RequestParam String processDefId) {
        try{
            Deployment deployment = FlowableEngine.getEngine().getRepositoryService().createDeployment()
                    .name("test")
                    .addClasspathResource("process/test1.bpmn20.xml")
                    .deploy();
        } catch (Exception e) {
            return new ResponseBase<>("500", e.getMessage()).toString();
        }

        return new ResponseBase<>().toString();
    }

    @GetMapping("/create")
    @ApiOperation(value = "Create a Processes.", notes = "创建一个流程实例")
    public List listAll() {
        logger.info("list all process info.");
        List<Process> re = new ArrayList<>();



        return re;
    }

}
