package edu.trojanow.trojandataaccesss;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class TestSupport {
	
	private static final JdbcTemplate JDBC_TEMPLATE = new JdbcTemplate(DataSourceFactory.getDataSource());
	
	@Before
	public void truncateTables(){
		for(final String myTableName : tables()){
			final String myQuery = "DELETE FROM " + myTableName;
			JDBC_TEMPLATE.execute(myQuery);
		}
	}
	
	public String[] tables(){
		return new String[]{};
	}
}
