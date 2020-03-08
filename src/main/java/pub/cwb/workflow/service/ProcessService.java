package pub.cwb.workflow.service;

import java.util.Map;

/**
 * @author athena
 */
public interface ProcessService {

    // create a process
    Map createProcess();

    // get all process
    Map listAllProcess();

    // delete a process
    Map deleteProcess();

}
