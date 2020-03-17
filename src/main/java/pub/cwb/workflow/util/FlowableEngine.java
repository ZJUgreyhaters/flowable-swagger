package pub.cwb.workflow.util;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;

public class FlowableEngine {

    public static ProcessEngine getEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

}
