package edu.trojanow.trojandataaccesss;

import edu.trojanow.trojanowmodel.Profile;

public interface ProfileDao {
	public Profile insert(final Profile pProfile);
	
	public long Authenticate(final String pEmail, final String pPassword);
}
