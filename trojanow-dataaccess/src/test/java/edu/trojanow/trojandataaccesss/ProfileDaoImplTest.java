package edu.trojanow.trojandataaccesss;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.trojanow.trojanowmodel.Profile;

public class ProfileDaoImplTest extends TestSupport{

	private static final String TEST_EMAIL = "TEST_EMAIL";

	private static final String TEST_PASSWORD = "TEST_PASSWORD";

	private static final String TEST_FULLNAME = "TEST_FULLNAME";

	public static final Profile TEST_PROFILE = new Profile(TEST_FULLNAME, TEST_PASSWORD, TEST_EMAIL);
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();

	@Override
	public String[] tables(){
		return new String[] {ProfileDaoImpl.TABLE_NAME};
	}
	
	@Test
	public void test() {		
		assertEquals(-1, mProfileDao.Authenticate(TEST_EMAIL, TEST_PASSWORD));
		
		final Profile myInsertedProfile = mProfileDao.insert(TEST_PROFILE);
		
		assertTrue(myInsertedProfile.getUserId() > 0);
		
		assertNotEquals(-1, mProfileDao.Authenticate(TEST_EMAIL, TEST_PASSWORD));
	}
}
