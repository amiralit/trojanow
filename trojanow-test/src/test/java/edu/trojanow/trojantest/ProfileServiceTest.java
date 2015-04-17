package edu.trojanow.trojantest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import edu.trojanow.trojandataaccesss.ProfileDaoImpl;

public class ProfileServiceTest extends TestSupport{
	
	@Override
	public String[] tables(){
		return new String[] {ProfileDaoImpl.TABLE_NAME};
	}

	@Test
	public void test() {
		final List<NameValuePair> myNameValuePairList = new ArrayList<NameValuePair>();
		
		myNameValuePairList.add(new BasicNameValuePair("fullname", "TEST_FULLNAME"));
		myNameValuePairList.add(new BasicNameValuePair("password", "TEST_PASSWORD"));
		myNameValuePairList.add(new BasicNameValuePair("email", "TEST_EMAIL"));
		
		final String myRespone = doPost("ProfileService", myNameValuePairList);
		
		try {
			JSONObject myResponseJsonObject = new JSONObject(myRespone);
			
			assertEquals(myResponseJsonObject.get("status"), "SUCCESS");
		} catch (JSONException e) {
			fail(e.getMessage());
		}
	}

}
