package pub.cwb.workflow.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pub.cwb.workflow.service.ProcessService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author athena
 */
@Service
public class ProcessServiceImpl implements ProcessService {
    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    // create a process
    @Override
    public Map createProcess() {
        Map re = new HashMap();

        return re;
    }

    // get all process
    @Override
    public Map listAllProcess() {
        Map re = new HashMap();

        return re;
    }

    // delete a process
    @Override
    public Map deleteProcess() {
        Map re = new HashMap();

        return re;
    }


}
