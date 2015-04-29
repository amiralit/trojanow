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

import edu.trojanow.trojanowmodel.Tweet;

public class TweetDaoImpl implements TweetDao {
	
	private static final DataSource DATA_SOURCE = DataSourceFactory.getDataSource();
	
	public static String TABLE_NAME  = "TWEETS";
	
	public enum Columns{
		USER_ID, TWEET_ID, MESSAGE;
	}

	private final InsertQuery mInsertQuery = new InsertQuery(DATA_SOURCE);
	
	private final GetByUserIdQuery mGetByUserIdQuery = new GetByUserIdQuery(DATA_SOURCE);
	
	private final LoadAllQuery mLoadAllQuery = new LoadAllQuery(DATA_SOURCE);
	
	public Tweet insert(final Tweet pTweet) {
		mInsertQuery.update(new Object[]{pTweet.getUserId(), pTweet.getMessage()});
		return null;
	}

	public Collection<Tweet> getByUserId(final long pUserId) {
		return mGetByUserIdQuery.execute(pUserId);
	}
	

	public Collection<Tweet> loadAll() {
		return mLoadAllQuery.execute();
	}

	private static class InsertQuery extends SqlUpdate{
		public InsertQuery(final DataSource pDataSource){
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format("INSERT INTO {0} ({1}, {2}) VALUES (?,?)", 
					TABLE_NAME, 
					Columns.USER_ID.toString(), 
					Columns.MESSAGE.toString());
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.USER_ID.toString(), Types.NUMERIC));
			declareParameter(new SqlParameter(Columns.MESSAGE.toString(), Types.VARCHAR));
			
			compile();	
		}
	}
	
	private static class GetByUserIdQuery extends SqlQuery<Tweet>{
		
		public GetByUserIdQuery(final DataSource pDataSource) {
			setDataSource(pDataSource);
			
			final String mySql = MessageFormat.format("SELECT * FROM {0} WHERE {1} = ?", 
					TABLE_NAME, 
					Columns.USER_ID.toString());
			setSql(mySql);
			
			declareParameter(new SqlParameter(Columns.USER_ID.toString(), Types.NUMERIC));
		}

		@Override
		protected RowMapper<Tweet> newRowMapper(Object[] arg0, Map<?, ?> arg1) {
			return TweetDaoImpl.getRowMapper(); 
		}	
	}
	
	private static class LoadAllQuery extends SqlQuery<Tweet>{
		public LoadAllQuery(final DataSource pDataSource){
			setDataSource(pDataSource);
			final String mySql = MessageFormat.format("SELECT * FROM {0}", 
					TABLE_NAME);
			
			setSql(mySql);
		}

		@Override
		protected RowMapper<Tweet> newRowMapper(Object[] arg0, Map<?,?> arg1) {
			return TweetDaoImpl.getRowMapper();
		}
	}
	
	private static RowMapper<Tweet> getRowMapper(){
		return new RowMapper<Tweet>() {

			public Tweet mapRow(ResultSet pResultSet, int pRowNum)
					throws SQLException {
				final long myUserId = pResultSet.getLong(Columns.USER_ID.toString());
				final long myTweetId = pResultSet.getLong(Columns.TWEET_ID.toString());
				final String myMessage = pResultSet.getString(Columns.MESSAGE.toString());
				
				return new Tweet(myMessage, myUserId, myTweetId);
			}
			
		};
	}
}
