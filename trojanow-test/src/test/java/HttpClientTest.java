import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;


public class HttpClientTest {

	@Test
	public void test() {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", "value1"));
		formparams.add(new BasicNameValuePair("password", "value2"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httppost = new HttpPost("http://localhost:8080/trojanow-web/Profile");
		httppost.setEntity(entity);
		
		CloseableHttpClient myHttpClient = HttpClients.createDefault();
		
		try {
			myHttpClient.execute(httppost);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
