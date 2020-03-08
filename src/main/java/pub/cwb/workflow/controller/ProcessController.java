package pub.cwb.workflow.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.cwb.workflow.service.impl.ProcessServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author athena
 */
@Api(tags = "Process Controller")
@RestController
@RequestMapping("pub/wf/process")
public class ProcessController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    ProcessServiceImpl processService;

    @GetMapping("/listAll")
    @ApiOperation(value = "Get All Processes.", notes = "listAll()")
    public List listAll() {
        logger.info("list all process info.");
        List<Process> re = new ArrayList<>();



        return re;
    }

}
