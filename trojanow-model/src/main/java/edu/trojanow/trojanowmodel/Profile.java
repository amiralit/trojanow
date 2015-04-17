package edu.trojanow.trojanowmodel;

public class Profile {
	private final String mFullname;
	private final String mPassword;
	private final String mEmail;
	
	public Profile(final String pFullname, final String pPassword, final String pEmail){
		mFullname = pFullname;
		mPassword = pPassword;
		mEmail = pEmail;
	}
	
	public String getFullname(){
		return mFullname;
	}
	
	public String getPassword(){
		return mPassword;
	}
	
	public String getEmail(){
		return mEmail;
	}
	
}
