package pub.cwb.workflow.util;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.springframework.context.annotation.Bean;

public class FlowableEngine {
    @Bean
    public static ProcessEngine getEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

}
