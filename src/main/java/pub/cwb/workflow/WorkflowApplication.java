package pub.cwb.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pub.cwb.workflow.util.FlowableEngine;

@SpringBootApplication
public class WorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
		// init engin
		FlowableEngine.init();
	}

}
