package pub.cwb.workflow.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pub.cwb.workflow.pojo.AssigneTaskReq;
import pub.cwb.workflow.util.FlowableEngine;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    public void assigneTask(AssigneTaskReq req) {
        logger.info("分配任务给用户： " + req.toString());
        if (StringUtils.isBlank(req.getTaskId()) || StringUtils.isBlank(req.getUserId())) {
            throw new RuntimeException("参数错误");
        }

        Task task = null;
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
}
