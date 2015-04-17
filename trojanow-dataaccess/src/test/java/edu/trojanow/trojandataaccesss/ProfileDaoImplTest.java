package edu.trojanow.trojandataaccesss;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.trojanow.trojanowmodel.Profile;

public class ProfileDaoImplTest extends TestSupport{


	
	private static final Profile TEST_PROFILE = new Profile("TEST_FULLNAME", "TEST_PASSWORD", "TEST_EMAIL");
	
	private final ProfileDao mProfileDao = new ProfileDaoImpl();

	@Override
	public String[] tables(){
		return new String[] {ProfileDaoImpl.TABLE_NAME};
	}
	
	@Test
	public void test() {		
		mProfileDao.insert(TEST_PROFILE);
	}
}
