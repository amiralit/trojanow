package edu.trojanow.trojantest;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.trojanow.trojandataaccesss.DataSourceFactory;


public abstract class TestSupport {
	
	private static String toString(final InputStream pInputStream) throws IOException{
		
		final StringBuilder myStringBuilder = new StringBuilder();
		
		final byte[] myBuffer = new byte[1024];
		
		int myNumberOfBytesRead = pInputStream.read(myBuffer);
		
		while(myNumberOfBytesRead != -1){
			myStringBuilder.append(new String(myBuffer).substring(0, myNumberOfBytesRead));
			
			myNumberOfBytesRead = pInputStream.read(myBuffer);
		}
		
		return myStringBuilder.toString();
	}

	protected String doPost(final String pApi, final List<NameValuePair> mNameValuePairs){
		UrlEncodedFormEntity myEntity = new UrlEncodedFormEntity(mNameValuePairs, Consts.UTF_8);
		HttpPost myHttpPost = new HttpPost(MessageFormat.format("http://localhost:8080/trojanweb/{0}", pApi));
		myHttpPost.setEntity(myEntity);
		
		CloseableHttpClient myHttpClient = HttpClients.createDefault();
		
		try {
			return toString(myHttpClient.execute(myHttpPost).getEntity().getContent());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		return null;
	}
	
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
