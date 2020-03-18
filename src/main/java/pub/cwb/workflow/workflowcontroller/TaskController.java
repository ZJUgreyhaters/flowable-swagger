package pub.cwb.workflow.workflowcontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.req.AssigneTaskReq;
import pub.cwb.workflow.pojo.req.BaseReq;
import pub.cwb.workflow.pojo.view.TaskVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author athena
 */
@Api(tags = "Task Controller")
@RestController
@RequestMapping("pub/wf/task")
public class TaskController {

    @GetMapping("/listAll")
    @ApiOperation(value = "List all task.", notes = "查询所有任务")
    public ResponseBase listAll() {
        ResponseBase<List<TaskVO>> re = new ResponseBase<>();

        List<Task> tasks;
        try{
            tasks = FlowableEngine.getEngine().getTaskService()
                    .createTaskQuery()
                    .orderByProcessInstanceId()
                    .desc()
                    .list();
        } catch (Exception e) {
            re.setCode("500");
            re.setMsg("获取任务失败");
            return re;
        }

        List<TaskVO> datas = new ArrayList<>();
        for (Task task: tasks
             ) {
            TaskVO tmp = new TaskVO(task);

            datas.add(tmp);
        }

        re.setData(datas);
        return re;
    }

    @PostMapping("/usertaskqry")
    @ApiOperation(value = "Query tasks of spec User.", notes = "查询用户的任务")
    public ResponseBase UserTaskQuery(@RequestBody BaseReq baseReq) {
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
        return re;
    }

    @PostMapping("/unassignedtaskqry")
    @ApiOperation(value = "Query unassigned tasks.", notes = "查询未分配的任务")
    public ResponseBase UnassiginedTaskQuery() {
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
            re.setMsg("获取任务失败");
            return re;
        }

        List<TaskVO> datas = new ArrayList<>();
        for (Task task: tasks
        ) {
            TaskVO tmp = new TaskVO(task);

            datas.add(tmp);
        }

        re.setData(datas);
        return re;
    }

    @PutMapping("/assignedtask")
    @ApiOperation(value = "assigned task to user.", notes = "分配任务给用户")
    public ResponseBase AssignedTaskToUser(@RequestBody AssigneTaskReq req) {
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

        if (tasks == null || tasks.size() <= 0) {
            re.setCode("500");
            re.setMsg("任务不存在, taskId : {}" + req.getTaskId());
        } else {
            FlowableEngine.getEngine().getTaskService().setAssignee(req.getTaskId(), req.getUserId());
        }

        return re;
    }

}
