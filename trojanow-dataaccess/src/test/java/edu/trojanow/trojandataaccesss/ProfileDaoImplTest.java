package edu.trojanow.trojandataaccesss;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.trojanow.trojanowmodel.Profile;

public class ProfileDaoImplTest extends TestSupport{

	private static final String TEST_PASSWORD = "TEST_PASSWORD";
	
	private static final String TEST_USERNAME = "TEST_USERNAME";
	
	private static final Profile TEST_PROFILE = new Profile(TEST_USERNAME, TEST_PASSWORD);
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();

	@Override
	public String[] tables(){
		return new String[] {ProfileDaoImpl.TABLE_NAME};
	}
	
	@Test
	public void test() {
		assertFalse(mProfileDao.authenticate(TEST_PROFILE));
		
		mProfileDao.insert(TEST_PROFILE);
		
		assertTrue(mProfileDao.authenticate(TEST_PROFILE));
	}
}
