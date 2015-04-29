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
import edu.trojanow.trojanowmodel.IResponse;
import edu.trojanow.trojanowmodel.Profile;
import edu.trojanow.trojanowmodel.Responses;

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
		try {
			
			final String myFailureAuthenticationResponse = doPost("AuthenticateService", myNameValuePairList);
			assertEquals(-1, new JSONObject(myFailureAuthenticationResponse).get("userId"));
			
			final String myRespone = doPost("ProfileService", myNameValuePairList);
			
			JSONObject myResponseJsonObject = new JSONObject(myRespone);
			
			assertEquals(myResponseJsonObject.get("status"), IResponse.Status.SUCCESS.toString());
			
			final String mySuccessAuthenticationResponse = doPost("AuthenticateService", myNameValuePairList);
			assertNotEquals(-1, new JSONObject(mySuccessAuthenticationResponse).get("userId"));
		} catch (JSONException e) {
			fail(e.getMessage());
		}
	}

}
