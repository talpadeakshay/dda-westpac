package com.westpac.dda.activiti.service.impl;

import java.util.Map;

import com.westpac.dda.activiti.service.IActivitiWorkflowService;

public class TestWorkflowService {
	
	IActivitiWorkflowService workflowService;
	
	
	
	public TestWorkflowService(IActivitiWorkflowService workflowService) {
		super();
		this.workflowService = workflowService;
	}



	public void startTestWorkflow(Map<String, Object> mapPayload){		
		
		workflowService.start("SimpleFlow", mapPayload);
	}
}
