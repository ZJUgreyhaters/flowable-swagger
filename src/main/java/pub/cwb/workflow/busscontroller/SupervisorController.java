package pub.cwb.workflow.busscontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.CompleteTaskReq;
import pub.cwb.workflow.pojo.PassReq;
import pub.cwb.workflow.pojo.view.TaskVO;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "主管待办列表")
    public ResponseBase todoList(@RequestParam String userId) {

        List<Task> tasks = FlowableEngine.getEngine().getTaskService().createTaskQuery()
                .processDefinitionKey(defKey).taskDefinitionKey("SupervisorApproval")
                .taskInvolvedUser(userId)
                .list();

        List<TaskVO> datas = new ArrayList<>();
        for (Task task : tasks) {
            TaskVO tmp = new TaskVO(task);

            tmp.setProcessVariables(FlowableEngine.getEngine().getRuntimeService().getVariables(task.getExecutionId()));

            datas.add(tmp);
        }

        return new ResponseBase(datas);
    }

    @PostMapping("/pass")
    @ApiOperation(value = "审批通过")
    public ResponseBase pass(@RequestBody PassReq req) {
        CompleteTaskReq completeTaskReq = new CompleteTaskReq();

        completeTaskReq.setTaskId(req.getTaskId());

        Map<String, Object> vars = new HashMap<>();
        vars.put("deny", false);
        vars.put("SupervisorComment", req.getUserId() + "says : " + req.getComment());

        completeTaskReq.setGlobalVars(vars);

        return taskService.completeTask(completeTaskReq);
    }
}
