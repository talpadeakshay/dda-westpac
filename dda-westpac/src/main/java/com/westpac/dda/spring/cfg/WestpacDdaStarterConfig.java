package com.westpac.dda.spring.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { HibernateDataStoreConfig.class,
		ActivitiProcessConfig.class })
public class WestpacDdaStarterConfig {

}
