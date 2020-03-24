package pub.cwb.workflow.workflowcontroller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.auth.pojo.ResponseBase;
import pub.cwb.workflow.pojo.view.ProcessInstanceHisVO;
import pub.cwb.workflow.pojo.view.ProcessInstanceVO;
import pub.cwb.workflow.util.FlowableEngine;

import java.util.LinkedList;
import java.util.List;


/**
 * @author athena
 */
@Api(tags = "Instance Controller")
@RestController
@RequestMapping("pub/wf/instance")
public class InstanceController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Value("${wf.process.defkey}")
    private String defKey;

    @GetMapping("/listAllProIns")
    @ApiOperation(value = "Get all process instances", notes = "获取运行的流程实例")
    public ResponseBase listAll() {
        ResponseBase<List<ProcessInstanceVO>> re = new ResponseBase<>();

        List<ProcessInstance> processInstances = FlowableEngine.getEngine()
                .getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(defKey)
                .orderByProcessDefinitionId().desc()
                .list();

        List<ProcessInstanceVO> datas = new LinkedList<>();

        for (ProcessInstance p : processInstances
             ) {
            ProcessInstanceVO tmp = new ProcessInstanceVO(p);

            datas.add(tmp);
        }

        re.setData(datas);
        return re;
    }


    @GetMapping("/listAllProInsHis")
    @ApiOperation(value = "Get all History process instances", notes = "获取历史的流程实例")
    public ResponseBase listAllHis() {
        ResponseBase<List<ProcessInstanceHisVO>> re = new ResponseBase<>();

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

        re.setData(datas);
        return re;
    }
}
