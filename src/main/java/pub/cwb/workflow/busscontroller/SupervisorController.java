package pub.cwb.workflow.busscontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.List;

@Api(tags = "SupervisorController Controller")
@RestController
@RequestMapping("pub/bs/sp")
public class SupervisorController {

    @Value("${wf.process.defkey}")
    private String defKey;

    @Autowired
    private HisService hisService;
    @Autowired
    private ReposityService reposityService;
    @Autowired
    private RunTimeService runTimeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/todoList")
    @ApiOperation(value = "待办列表")
    public ResponseBase todoList() {

        List<Task> tasks1 = FlowableEngine.getEngine().getTaskService().createTaskQuery()
                .processDefinitionKey("HolidayProcess2").taskDefinitionKey("SupervisorApproval").list();
        List<Task> tasks = FlowableEngine.getEngine().getTaskService().createTaskQuery()
                .processDefinitionKey("HolidayProcess2").taskDefinitionKey("HolidayRequest").list();
        tasks.addAll(tasks1);
        return new ResponseBase(tasks);
    }
}
