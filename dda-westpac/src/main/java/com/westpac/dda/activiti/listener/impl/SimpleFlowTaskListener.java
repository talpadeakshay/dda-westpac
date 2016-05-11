package com.westpac.dda.activiti.listener.impl;

import org.activiti.engine.delegate.DelegateTask;

public class SimpleFlowTaskListener  {

	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println(delegateTask);
	}

}
