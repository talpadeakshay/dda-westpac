package com.westpac.dda.activiti.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

public interface IActivitiWorkflowService {

	public ProcessInstance start(String workFlowId, Map<String, Object> payload);
}
