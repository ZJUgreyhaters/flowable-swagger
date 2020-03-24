package pub.cwb.workflow.busscontroller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liquibase.pro.packaged.M;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.BaseReq;
import pub.cwb.workflow.pojo.HisTaskReq;
import pub.cwb.workflow.pojo.HolidayReq;
import pub.cwb.workflow.pojo.view.ProcessInstanceVO;
import pub.cwb.workflow.service.impl.HisService;
import pub.cwb.workflow.service.impl.ReposityService;
import pub.cwb.workflow.service.impl.RunTimeService;
import pub.cwb.workflow.service.impl.TaskService;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "EmployeeController Controller")
@RestController
@RequestMapping("pub/bs/employee")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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

    @PostMapping("/requestHoliday")
    @ApiOperation(value = "提交假期申请")
    public ResponseBase requestHoliday(@RequestBody HolidayReq req) {
        BaseReq createProcess = new BaseReq();
        Map<String, Object> gloableVars =  new HashMap<>();
        gloableVars.put("employee", req.getUserId());

        // 创建流程实例
        ProcessInstanceVO process = runTimeService.createProcess(createProcess);

        // 查询用户任务（提交假期）
        HisTaskReq hisTaskReq = new HisTaskReq();
        hisTaskReq.setUserId(req.getUserId());
        hisTaskReq.setProcessInstanceId(process.getProcessInstanceId());
        Task task = FlowableEngine.getEngine().getTaskService().createTaskQuery()
                .taskDefinitionKey("HolidayRequest")
                .processInstanceId(process.getProcessInstanceId())
                .taskInvolvedUser(req.getUserId()).singleResult();

        // 提交假期信息
        Map complvars = new HashMap();
        complvars.put("user", req.getUserId());
        complvars.put("days", req.getDays());
        complvars.put("reason", req.getReason());
        complvars.put("startDate", req.getStartDate());
        complvars.put("endDate", req.getEndDate());
        complvars.put("status", "2");

        FlowableEngine.getEngine().getTaskService().complete(task.getId(), complvars);

        return new ResponseBase();
    }


}
