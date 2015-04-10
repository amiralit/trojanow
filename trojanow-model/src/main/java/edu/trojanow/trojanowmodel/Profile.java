package edu.trojanow.trojanowmodel;

public class Profile {
	private final String mUsername;
	private final String mPassword;
	
	public Profile (final String pUsername, final String pPassword){
		mUsername = pUsername;
		mPassword = pPassword;
	}
	
	public String getUsername(){
		return mUsername;
	}
	
	public String getPassword(){
		return mPassword;
	}
	
	
}
