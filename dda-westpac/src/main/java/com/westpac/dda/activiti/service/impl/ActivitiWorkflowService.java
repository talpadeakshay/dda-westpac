package com.westpac.dda.activiti.service.impl;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;

import com.westpac.dda.activiti.service.IActivitiWorkflowService;

public class ActivitiWorkflowService implements IActivitiWorkflowService{

	private RuntimeService runtimeService;
	private TaskService taskService;

	public ActivitiWorkflowService(RuntimeService runtimeService,
			TaskService taskService) {
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	public ProcessInstance start(String workFlowId, Map<String, Object> payload) {
		ProcessInstance process = this.runtimeService
				.startProcessInstanceByKey(workFlowId, payload);
		return process;
	}	
}
