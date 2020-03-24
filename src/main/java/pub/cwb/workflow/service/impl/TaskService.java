package pub.cwb.workflow.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.AssigneTaskReq;
import pub.cwb.workflow.pojo.CompleteTaskReq;
import pub.cwb.workflow.util.FlowableEngine;

/**
 * @author athena
 */
@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    /**
     * 分配任务给用户
     * @param req
     */
    public void assigneTask(AssigneTaskReq req) {
        logger.info("分配任务给用户： " + req.toString());
        if (StringUtils.isBlank(req.getTaskId()) || StringUtils.isBlank(req.getUserId())) {
            throw new RuntimeException("参数错误");
        }

        Task task;
        try {
            task = FlowableEngine.getEngine().getTaskService()
                    .createTaskQuery().taskId(req.getTaskId()).singleResult();
            if (task == null) {
                throw new RuntimeException("任务不存在, taskID : " + req.getTaskId());
            }

            FlowableEngine.getEngine().getTaskService().setAssignee(req.getTaskId(), req.getUserId());
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    /**
     * 完成任务
     */
    public ResponseBase completeTask(CompleteTaskReq req) {
        try {
            FlowableEngine.getEngine().getTaskService().complete(req.getTaskId(), req.getGlobalVars());
        } catch (Exception e) {
            return new ResponseBase("500", "完成任务失败");
        }
        return new ResponseBase("200", "Success.");
    }
}
