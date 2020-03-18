package pub.cwb.workflow.util;

import liquibase.pro.packaged.E;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class FlowableEngine {
    private static final Logger logger = LoggerFactory.getLogger(FlowableEngine.class);

    private static ProcessEngine engine;

    public static ProcessEngine getEngine() {
        return engine;
    }

    public static void init() {
        //ProcessEngine engine = null;
        try{
            engine = ProcessEngines.getDefaultProcessEngine();
        } catch (Exception e) {
            logger.error("Initiate Engine fail.");
        }
        if (engine != null){
            logger.info("Engine start successful.");
        } else {
            logger.info("Engine start fail. Something went wrong.");
        }

    }
}
