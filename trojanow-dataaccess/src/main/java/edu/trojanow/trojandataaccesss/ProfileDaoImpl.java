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
		FULLNAME, PASSWORD, EMAIL, USER_ID
	}
	
	private final InsertQuery mInsertQuery = new InsertQuery(DATA_SOURCE);
	
	private final AuthenticateQuery mAuthenticateQuery = new AuthenticateQuery(DATA_SOURCE);

	public void insert(final Profile pProfile) {
		mInsertQuery.update(new Object[] {pProfile.getFullname(), pProfile.getPassword(), pProfile.getEmail()});
	}
	
	public long Authenticate(String pEmail, String pPassword) {
		
		final Collection<Long> myResults = mAuthenticateQuery.execute(new Object[]{pEmail, pPassword});
		
		if (myResults.isEmpty()){
			return -1;
		}
		
		return myResults.iterator().next();
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
	
	private static class AuthenticateQuery extends SqlQuery<Long>{
		
		public AuthenticateQuery(final DataSource pDataSource){
			super();
			
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format("SELECT {0} FROM {1} WHERE {2} = ? AND {3} = ?", 
					Columns.USER_ID,
					ProfileDaoImpl.TABLE_NAME,
					Columns.EMAIL,
					Columns.PASSWORD);
			
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.EMAIL.toString(), Types.VARCHAR));
			declareParameter(new SqlParameter(Columns.PASSWORD.toString(), Types.VARCHAR));
			
			compile();
		}

		@Override
		protected RowMapper<Long> newRowMapper(Object[] arg0, Map<?,?> arg1) {
			return new RowMapper<Long>() {

				public Long mapRow(ResultSet pResultSet, int pRowNum)
						throws SQLException {
					return pResultSet.getLong(Columns.USER_ID.toString());
				}
			};
		}	
	}
}
