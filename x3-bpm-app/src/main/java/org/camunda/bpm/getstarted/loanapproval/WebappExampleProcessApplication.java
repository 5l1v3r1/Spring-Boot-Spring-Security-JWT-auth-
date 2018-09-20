package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableProcessApplication
public class WebappExampleProcessApplication {

	
	public static void main(String... args) {
		SpringApplication.run(WebappExampleProcessApplication.class, args);
	}
	
//	@EventListener
//	private void processPostDeploy(PostDeployEvent event) {
//		runtimeService.startProcessInstanceByKey("loanApproval");
//	}

//	public void testSchedule() {
//
//	}
}