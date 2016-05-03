package com.ejemplo.persistencia;

import javax.sql.DataSource;

public class AbstractJDBCDao {

	protected DataSource dataSource;

	public AbstractJDBCDao() {
		super();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}