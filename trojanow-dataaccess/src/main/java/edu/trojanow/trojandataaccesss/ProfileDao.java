package edu.trojanow.trojandataaccesss;

import edu.trojanow.trojanowmodel.Profile;

public interface ProfileDao {
	public void insert(final Profile pProfile);
	
	public boolean Authenticate(final String pEmail, final String pPassword);
}
