package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.HisTaskReq;
import pub.cwb.workflow.pojo.VarsQryReq;
import pub.cwb.workflow.pojo.AssigneTaskReq;
import pub.cwb.workflow.pojo.req.CompleteTaskReq;
import pub.cwb.workflow.pojo.view.TaskVO;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;
import pub.cwb.workflow.util.FlowableEngine;

import javax.validation.Valid;
import java.util.List;

/**
 * @author athena
 */
@Api(tags = "Task Controller")
@RestController
@RequestMapping("pub/wf/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private HisService hisService;
    @Autowired
    private ReposityService reposityService;
    @Autowired
    private RunTimeService runTimeService;
    @Autowired
    private TaskService taskService;

    @Value("${wf.process.defkey}")
    private String defKey;

    @PostMapping("/getTask")
    @ApiOperation(value = "获取[用户/为分配/历史]任务", notes = "")
    public ResponseBase getTaskQuery(@RequestBody(required = true) HisTaskReq req) {
        return new ResponseBase(hisService.getHisTask(req));
    }


    @PutMapping("/AssignedTask")
    @ApiOperation(value = "assigned task to user.", notes = "分配任务给用户")
    public ResponseBase assignedTaskToUser(@Valid @RequestBody AssigneTaskReq req) {

        taskService.assigneTask(req);
        return new ResponseBase();
    }

    @PostMapping("/complete")
    @ApiOperation(value = "complete a task.", notes = "完成一个任务")
    public ResponseBase completetask(@Valid @RequestBody CompleteTaskReq req) {

        try {
            FlowableEngine.getEngine().getTaskService().complete(req.getTaskId(), req.getGlobalVars());
        } catch (Exception e) {
            return new ResponseBase("500", "完成任务失败");
        }

        return new ResponseBase();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete a Task.", notes = "删除一个任务")
    public ResponseBase deletetask(@Valid @RequestBody CompleteTaskReq req) {

        try {
            FlowableEngine.getEngine().getTaskService().deleteTask(req.getTaskId());
        } catch (Exception e) {
            return new ResponseBase("500", "无法删除任务");
        }

        return new ResponseBase();
    }

    @PostMapping("/getVars")
    @ApiOperation(value = "获取流程/任务变量", notes = "")
    public ResponseBase getVars(@RequestBody VarsQryReq req) {
        // 优先taskId查询
        return new ResponseBase(runTimeService.getVars(req));
    }

}
