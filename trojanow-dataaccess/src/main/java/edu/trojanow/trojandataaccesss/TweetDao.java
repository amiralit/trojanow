package edu.trojanow.trojandataaccesss;

import java.util.Collection;

import edu.trojanow.trojanowmodel.Tweet;

public interface TweetDao {
	public Tweet insert(final Tweet pTweet);
	public Collection<Tweet> getByUserId(final long pUserId);
	public Collection<Tweet> loadAll();
}
