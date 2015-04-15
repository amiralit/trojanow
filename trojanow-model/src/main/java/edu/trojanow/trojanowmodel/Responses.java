package edu.trojanow.trojanowmodel;


public enum Responses implements IResponse {
	PROFILE_INSERT_SUCCESS(1, "Profile was saved successfully", Status.SUCCESS),
	PROFILE_INSERT_FAILURE(2, "Unable to save Profile", Status.FAILURE)
	;
	
	private final long mCode;
	private final String mMessage;
	private final Status mStatus;
	
	private Responses(final long pCode, final String pMessage, final Status pStatus) {
		mCode = pCode;
		mMessage = pMessage;
		mStatus = pStatus;
	}

	public long getCode() {
		return mCode;
	}

	public String getMessage() {
		return mMessage;
	}

	public String getStatus() {
		return mStatus.toString();
	}

}
