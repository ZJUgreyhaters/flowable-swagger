package pub.cwb.workflow.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author athena
 */
@Api(tags = "Instance Controller")
@RestController
@RequestMapping("pub/wf/instance")
public class InstanceController {
    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);


    @GetMapping("/listAll")
    @ApiOperation(value = "Get all instances", notes = "listAll()")
    public List listAll() {
        List<String> re = new ArrayList<>();

        return re;
    }

}
