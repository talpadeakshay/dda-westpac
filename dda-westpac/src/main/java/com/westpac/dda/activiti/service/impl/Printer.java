package com.westpac.dda.activiti.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;

public class Printer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922912909800785729L;

	public void printMessage() {
		System.out.println("hello world");
	}
	
	public void printService() {
		System.out.println("Printer service");
	}
	
	public void taskAccepted() {
		System.out.println("taskAccepted");
	}
	
	public void taskRejected() {
		System.out.println("taskRejected");
	}
	
	public void assignTaskGroup(final Map<String, Object> payload, final String... candidateGroup) {
		System.out.println(payload);
		System.out.println(candidateGroup);
		System.out.println("assignTaskGroup");
	}
	
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println(delegateTask);
	}
}
