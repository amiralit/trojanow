package edu.trojanow.trojanowmodel;

public interface IResponse {
	public enum Status{
		FAILURE, SUCCESS
	}
	
	public long getCode();
	public String getMessage();
	public String getStatus();
}
