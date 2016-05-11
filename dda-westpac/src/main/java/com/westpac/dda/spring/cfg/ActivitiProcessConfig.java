package com.westpac.dda.spring.cfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.westpac.dda.activiti.listener.impl.WestpacEventListener;
import com.westpac.dda.activiti.service.IActivitiWorkflowService;
import com.westpac.dda.activiti.service.impl.ActivitiWorkflowService;
import com.westpac.dda.activiti.service.impl.Printer;

@PropertySources({
		@PropertySource(value = { "classpath:/jdbc.properties",
				"classpath:/activiti.workfow.properties" }, ignoreResourceNotFound = true),
		@PropertySource(value = { "file:${DDA_CONFIG_DIR}/jdbc.properties",
				"file:${DDA_CONFIG_DIR}/activiti.workfow.properties" }, ignoreResourceNotFound = true) })
@Configuration
public class ActivitiProcessConfig {

	private static final Logger LOGGER =
			LoggerFactory.getLogger(ActivitiProcessConfig.class);
	@Autowired
	DataSource dataSource;

	@Autowired
	Environment env;

	@Bean
	@DependsOn("getDataSource")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	@DependsOn(value = { "dataSourceTransactionManager" })
	public SpringProcessEngineConfiguration processEngineConfiguration() {
		SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
		configuration.setDataSource(dataSource);
		configuration.setDatabaseType(env.getProperty("activiti.dbtype"));
		configuration.setTransactionsExternallyManaged(true);
		configuration.setTransactionManager(dataSourceTransactionManager());
		configuration.setDatabaseSchemaUpdate(env.getProperty("activiti.database_schema_update"));
		configuration.setCreateDiagramOnDeploy(false);
		configuration.setJobExecutorActivate(false);
		// configuration.setDatabaseSchema(env.getProperty("activiti.database.schema"));
		// configuration.setEventListeners(registerListners());
		return configuration;
	}

	// @Bean
	public List<ActivitiEventListener> registerListners() {
		List<ActivitiEventListener> listListner = new ArrayList<ActivitiEventListener>();
		WestpacEventListener listen = new WestpacEventListener();
		listListner.add(listen);
		return listListner;
	}

	/*
	 * @Bean public ActivitiEventListener westpacEventListener() { WestpacEventListener listen = new
	 * WestpacEventListener(); return listen; }
	 */

	@Bean
	@DependsOn("processEngineConfiguration")
	public ProcessEngineFactoryBean processEngineFactoryBean() {
		ProcessEngineFactoryBean bean = new ProcessEngineFactoryBean();
		bean.setProcessEngineConfiguration(processEngineConfiguration());
		return bean;
	}

	@Bean
	@DependsOn("processEngineFactoryBean")
	RuntimeService runtimeService() {
		return processEngineFactoryBean().getProcessEngineConfiguration().getRuntimeService();
	}

	@Bean
	@DependsOn("processEngineFactoryBean")
	TaskService taskService() {
		return processEngineFactoryBean().getProcessEngineConfiguration().getTaskService();
	}

	@Bean
	@DependsOn("processEngineFactoryBean")
	public RepositoryService repositoryService() {
		return processEngineFactoryBean().getProcessEngineConfiguration().getRepositoryService();
	}

	@Bean
	@DependsOn("repositoryService")
	public Void loadWorkflows()
	{
		DeploymentBuilder deployer = repositoryService().createDeployment();
		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		String workflowPaths[] = env.getRequiredProperty("activiti.workflowPaths").split(",");
		String as[] = workflowPaths;
		int i = as.length;
		for (int j = 0; j < i; j++)
		{
			String path = as[j];
			try
			{
				Resource aresource[] = pmrpr.getResources(path);
				int k = aresource.length;
				for (int l = 0; l < k; l++)
				{
					Resource resource = aresource[l];
					LOGGER.info((new StringBuilder()).append("Adding process definition from ")
							.append(resource.getDescription()).toString());
					deployer.addInputStream(resource.getFilename(), resource.getInputStream());
				}

			} catch (IOException e)
			{
				LOGGER.warn((new StringBuilder()).append("Could not list resources from ").append(path)
						.append(".").toString(), e);
			}
		}

		deployer.deploy();
		LOGGER.info("Number of process definitions: " + repositoryService().createProcessDefinitionQuery().count());
		return null;
	}

	@Bean
	public IActivitiWorkflowService workFlowService() {
		IActivitiWorkflowService workflowService = new ActivitiWorkflowService(runtimeService(), taskService());
		return workflowService;
	}

	@Bean
	public Printer getPrinter() {
		Printer print = new Printer();
		return print;
	}
}
