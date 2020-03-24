package pub.cwb.workflow.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pub.cwb.workflow.constants.TaskState;
import pub.cwb.workflow.pojo.HisTaskReq;
import pub.cwb.workflow.pojo.view.HistoricTaskVO;
import pub.cwb.workflow.pojo.view.ProcessInstanceHisVO;
import pub.cwb.workflow.pojo.view.TaskVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author athena
 */
@Service
public class HisService {
    private static final Logger logger = LoggerFactory.getLogger(HisService.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    /**
     * 获取任务信息
     * @param req
     * @return
     */
    public List<? extends Object> getHisTask(HisTaskReq req) {
        logger.info("获取任务: " + req.toString());
        if((StringUtils.isNotBlank(req.getUserId()) &&
                        TaskState.notAssigined.getFlag().equals(req.getIsAssigned()))) {
            throw new RuntimeException("参数错误");
        }

        List<Task> tasks = null;
        List<HistoricTaskInstance> hisTasks = null;
        if (TaskState.Finished.getFlag().equals(req.getFinished())) {
            // 查询历史任务 1 - 已完成
            hisTasks = FlowableEngine.getEngine().getHistoryService().createHistoricTaskInstanceQuery().processDefinitionKey(defKey).list();
        } else if (TaskState.notAssigined.getFlag().equals(req.getIsAssigned())) {
            // 查询未分配用户的任务 0 - not assigned
            tasks = FlowableEngine.getEngine().getTaskService().createTaskQuery().processDefinitionKey(defKey).taskUnassigned().list();
        } else if (StringUtils.isNotBlank(req.getUserId())) {
            tasks = FlowableEngine.getEngine().getTaskService().createTaskQuery().processDefinitionKey(defKey).taskInvolvedUser(req.getUserId()).list();
        }

        List<TaskVO> datas = new ArrayList<>();
        List<HistoricTaskVO> hisDatas = new ArrayList<>();
        if (tasks == null && hisTasks == null) {
            return new ArrayList<>();
        } else if (tasks != null){
            for (Task task : tasks) {
                TaskVO tmp = new TaskVO(task);
                datas.add(tmp);
            }
            return datas;
        } else {
            for (HistoricTaskInstance hisTask : hisTasks) {
                HistoricTaskVO tmp = new HistoricTaskVO(hisTask);
                hisDatas.add(tmp);
            }
            return hisDatas;
        }
    }

    /**
     * 获取历史流程实例
     * @return
     */
    public List<? extends Object> getHisProcessInstance() {

        List<HistoricProcessInstance> processInstances = FlowableEngine.getEngine()
                .getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processDefinitionKey(defKey)
                .orderByProcessDefinitionId()
                .desc()
                .list();

        List<ProcessInstanceHisVO> datas = new LinkedList<>();

        for (HistoricProcessInstance p : processInstances
        ) {
            ProcessInstanceHisVO tmp = new ProcessInstanceHisVO(p);

            datas.add(tmp);
        }

        return datas;
    }
}
