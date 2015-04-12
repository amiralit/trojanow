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
		USERNAME, PASSWORD
	}
	
	private final InsertQuery mInsertQuery = new InsertQuery(DATA_SOURCE);
	
	private final AuthenticateQuery mAuthenticateQuery = new AuthenticateQuery(DATA_SOURCE);

	public void insert(final Profile pProfile) {
		mInsertQuery.update(new Object[] {pProfile.getUsername(), pProfile.getPassword()});
	}
	
	public boolean authenticate(Profile pProfile) {
		final Collection<Integer> myCounts = mAuthenticateQuery.execute(pProfile);
		
		// TODO throw RuntimeException if myCounts.isEmpty();
		
		return myCounts.iterator().next() > 0;
	}
	
	private static class InsertQuery extends SqlUpdate{
		public InsertQuery(final DataSource pDataSource){
			super();
			
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format(
					"INSERT INTO {0} ({1}, {2}) VALUES (?,?)",
					ProfileDaoImpl.TABLE_NAME, 
					Columns.USERNAME.toString(),
					Columns.PASSWORD.toString());
			
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.USERNAME.toString(), Types.VARCHAR));
			declareParameter(new SqlParameter(Columns.PASSWORD.toString(), Types.VARCHAR));

			compile();	
		}
	}
	
	private static class AuthenticateQuery extends SqlQuery<Integer>{
		
		private static final String COUNT = "COUNT";
		
		public AuthenticateQuery(final DataSource pDataSource){
			super();
			
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format("SELECT COUNT(*) AS {3} FROM {0} WHERE {1} = ? AND {2} = ?",
					ProfileDaoImpl.TABLE_NAME,
					Columns.USERNAME.toString(),
					Columns.PASSWORD.toString(),
					COUNT);
			
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.USERNAME.toString(), Types.VARCHAR));
			declareParameter(new SqlParameter(Columns.PASSWORD.toString(), Types.VARCHAR));

			compile();					
		}

		@Override
		protected RowMapper<Integer> newRowMapper(Object[] pParams, Map<?, ?> pContext) {
			return new RowMapper<Integer>() {
				public Integer mapRow(ResultSet pResultSet, int pRowNum)
						throws SQLException {
					return pResultSet.getInt(COUNT);
				}
			};
		}
		
		public Collection<Integer> execute(final Profile pProfile){
			return this.execute(new Object[]{pProfile.getUsername(), pProfile.getPassword()});
		}
	}
}
