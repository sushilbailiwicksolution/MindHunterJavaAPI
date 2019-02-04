package com.bailiwick.game_service.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public class JDBCTemplateService {

	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;

	public JDBCTemplateService(org.apache.commons.dbcp.BasicDataSource dataSource,
			PlatformTransactionManager transactionManager) {
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("SELECT 1");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.transactionManager = transactionManager;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}
}
