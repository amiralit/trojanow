package edu.trojanow.trojanowmodel;

public class Profile {
	private final String mFullname;
	private final String mPassword;
	private final String mEmail;
	private final long mUserId;
	
	public Profile(final String pFullname, final String pPassword, final String pEmail){
		this(pFullname, pPassword, pEmail, 0);
	}
	
	public Profile(final String pFullname, final String pPassword, final String pEmail, final long pUserId){
		mFullname = pFullname;
		mPassword = pPassword;
		mEmail = pEmail;
		mUserId = pUserId;
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
	
	public long getUserId(){
		return mUserId;
	}
	
	public static class UserId{
		private final long mUserId;
		
		public UserId(final long pUserId){
			mUserId = pUserId;
		}
		
		public long getUserId(){
			return mUserId;
		}
	}
	
}
