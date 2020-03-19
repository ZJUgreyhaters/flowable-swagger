package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.req.AssigneTaskReq;
import pub.cwb.workflow.pojo.req.BaseReq;
import pub.cwb.workflow.pojo.req.CompleteTaskReq;
import pub.cwb.workflow.pojo.view.HistoricTaskVO;
import pub.cwb.workflow.pojo.view.TaskVO;
import pub.cwb.workflow.util.FlowableEngine;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author athena
 */
@Api(tags = "Task Controller")
@RestController
@RequestMapping("pub/wf/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/HisTask")
    @ApiOperation(value = "Query Historic task.", notes = "查询历史任务")
    public ResponseBase hisTask() {
        ResponseBase<List<HistoricTaskVO>> re = new ResponseBase<>();

        List<HistoricTaskInstance> tasks;
        try{
            tasks = FlowableEngine.getEngine().getHistoryService()
                    .createHistoricTaskInstanceQuery()
                    .orderByProcessDefinitionId()
                    .asc()
                    .list();
        } catch (Exception e) {
            re.setCode("500");
            re.setMsg("获取任务失败");
            return re;
        }

        List<HistoricTaskVO> datas = new ArrayList<>();
        for (HistoricTaskInstance task: tasks
        ) {
            HistoricTaskVO tmp = new HistoricTaskVO(task);

            datas.add(tmp);
        }

        re.setData(datas);
        logger.info("data: {0}", re.toString());
        return re;
    }

    @PostMapping("/UserTaskQry")
    @ApiOperation(value = "Query tasks of spec User.", notes = "查询用户的任务")
    public ResponseBase userTaskQuery(@RequestBody BaseReq baseReq) {
        ResponseBase<List<TaskVO>> re = new ResponseBase<>();
        if (StringUtils.isBlank(baseReq.getUserId())) {
            re.setMsg("User Id is missed.");
            re.setCode("500");
            return re;
        }

        List<Task> tasks;
        try{
            tasks = FlowableEngine.getEngine().getTaskService()
                    .createTaskQuery()
                    .taskInvolvedUser(baseReq.getUserId())
                    .orderByProcessInstanceId()
                    .desc()
                    .list();
        } catch (Exception e) {
            re.setCode("500");
            re.setMsg("获取任务失败,userId : " + baseReq.getUserId());
            return re;
        }

        List<TaskVO> datas = new ArrayList<>();
        for (Task task: tasks
        ) {
            TaskVO tmp = new TaskVO(task);

            datas.add(tmp);
        }

        re.setData(datas);
        logger.info("data: {0}", re.toString());
        return re;
    }

    @PostMapping("/UnassignedTaskQry")
    @ApiOperation(value = "Query unassigned tasks.", notes = "查询未分配的任务")
    public ResponseBase unassiginedTaskQuery() {
        ResponseBase<List<TaskVO>> re = new ResponseBase<>();

        List<Task> tasks;
        try{
            tasks = FlowableEngine.getEngine().getTaskService()
                    .createTaskQuery()
                    .taskUnassigned()
                    .orderByProcessInstanceId()
                    .desc()
                    .list();
        } catch (Exception e) {
            re.setCode("500");
            re.setMsg("查询未分配任务失败");
            return re;
        }

        List<TaskVO> datas = new ArrayList<>();
        for (Task task: tasks
        ) {
            TaskVO tmp = new TaskVO(task);

            datas.add(tmp);
        }

        re.setData(datas);
        logger.info("data: {0}", re.toString());
        return re;
    }

    @PutMapping("/AssignedTask")
    @ApiOperation(value = "assigned task to user.", notes = "分配任务给用户")
    public ResponseBase assignedTaskToUser(@Valid @RequestBody AssigneTaskReq req) {
        ResponseBase<List<TaskVO>> re = new ResponseBase<>();
        if (StringUtils.isBlank(req.getTaskId()) || StringUtils.isBlank(req.getUserId())) {
            re.setMsg("Param is missed.");
            re.setCode("500");
            return re;
        }

        List<Task> tasks;
        try{
            tasks = FlowableEngine.getEngine().getTaskService()
                    .createTaskQuery()
                    .taskId(req.getTaskId())
                    .orderByProcessInstanceId()
                    .desc()
                    .list();
        } catch (Exception e) {
            re.setCode("500");
            re.setMsg("获取任务失败");
            return re;
        }

        if (tasks == null || tasks.isEmpty()) {
            re.setCode("500");
            re.setMsg("任务不存在, taskId : {}" + req.getTaskId());
        } else {
            FlowableEngine.getEngine().getTaskService().setAssignee(req.getTaskId(), req.getUserId());
        }

        return re;
    }

    @PostMapping("/complete")
    @ApiOperation(value = "complete a task.", notes = "完成一个任务")
    public ResponseBase completetask(@Valid @RequestBody CompleteTaskReq req) {
        ResponseBase re = new ResponseBase();

        Map<String, Object> gloabVars = new HashMap<>();
        gloabVars.put("approve", true);

        try {
            FlowableEngine.getEngine().getTaskService().complete(req.getTaskId(), gloabVars);
        } catch (Exception e) {
            re.setMsg("无法完成任务");
        }
        re.setMsg("success complete task.");
        return re;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete a Task.", notes = "删除一个任务")
    public ResponseBase deletetask(@Valid @RequestBody CompleteTaskReq req) {
        ResponseBase re = new ResponseBase();

        try {
            FlowableEngine.getEngine().getTaskService().deleteTask(req.getTaskId());
        } catch (Exception e) {
            re.setMsg("无法完成任务");
        }

        return re;
    }

}
