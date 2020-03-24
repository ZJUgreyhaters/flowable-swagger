package pub.cwb.workflow.busscontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.List;

@Api(tags = "HRController Controller")
@RestController
@RequestMapping("pub/bs/hr")
public class HRController {

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
    @ApiOperation(value = "HR待办列表")
    public ResponseBase todoList(@RequestParam String userId) {


        List<Task> tasks = FlowableEngine.getEngine().getTaskService().createTaskQuery()
                .processDefinitionKey(defKey).taskDefinitionKey("HrReview")
                .taskInvolvedUser(userId)
                .list();

        return new ResponseBase(tasks);
    }
}
