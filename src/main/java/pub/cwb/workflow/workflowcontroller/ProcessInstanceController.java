package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.BaseReq;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;


import javax.validation.constraints.NotBlank;


@Api(tags = "Process & Instance Controller")
@RestController
@RequestMapping("pub/wf/processInstance")
public class ProcessInstanceController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessInstanceController.class);

    @Autowired
    private HisService hisService;
    @Autowired
    private ReposityService reposityService;
    @Autowired
    private RunTimeService runTimeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/deploy")
    @ApiOperation(value = "部署流程", notes = "部署流程")
    public ResponseBase deployment(@RequestParam(required = true) @NotBlank String processDefkey) {

        return new ResponseBase(reposityService.deploymentProcess(processDefkey));
    }

    @GetMapping("/deployquery")
    @ApiOperation(value = "查询已部署流程", notes = "查询已部署流程")
    public ResponseBase deployquery(@RequestParam(required = true) @NotBlank String processDefKey) {
        return new ResponseBase(reposityService.deploymentQuery(processDefKey));
    }

    @PostMapping("/createProcess")
    @ApiOperation(value = "创建一个流程实例", notes = "创建一个流程实例")
    public ResponseBase createProcessInstance(@RequestBody BaseReq req) {
        return new ResponseBase(runTimeService.createProcess(req));
    }

    @GetMapping("/listAllProInsHis")
    @ApiOperation(value = "获取历史流程实例", notes = "获取历史的流程实例")
    public ResponseBase listAllHis() {
        return new ResponseBase(hisService.getHisProcessInstance());
    }

    @GetMapping("/listAllProIns")
    @ApiOperation(value = "获取运行的流程实例", notes = "获取运行的流程实例")
    public ResponseBase listAll() {
        return new ResponseBase(runTimeService.getProcessInstance());
    }
}
