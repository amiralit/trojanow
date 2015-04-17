package edu.trojanow.trojandataaccesss;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import edu.trojanow.trojanowmodel.Profile;

public class ProfileDaoImpl implements ProfileDao {
	
	public static final String TABLE_NAME = "USERS";
	
	private static final DataSource DATA_SOURCE = DataSourceFactory.getDataSource();
	
	public enum Columns{
		FULLNAME, PASSWORD, EMAIL
	}
	
	private final InsertQuery mInsertQuery = new InsertQuery(DATA_SOURCE);

	public void insert(final Profile pProfile) {
		mInsertQuery.update(new Object[] {pProfile.getFullname(), pProfile.getPassword(), pProfile.getEmail()});
	}
	
	
	private static class InsertQuery extends SqlUpdate{
		public InsertQuery(final DataSource pDataSource){
			super();
			
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format(
					"INSERT INTO {0} ({1}, {2}, {3}) VALUES (?,?,?)",
					ProfileDaoImpl.TABLE_NAME, 
					Columns.FULLNAME.toString(),
					Columns.PASSWORD.toString(),
					Columns.EMAIL.toString());
			
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.FULLNAME.toString(), Types.VARCHAR));
			declareParameter(new SqlParameter(Columns.PASSWORD.toString(), Types.VARCHAR));
			declareParameter(new SqlParameter(Columns.EMAIL.toString(), Types.VARCHAR));

			compile();	
		}
	}
}
