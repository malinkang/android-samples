package com.mamating.bean;

/**
 * 
 * @author malinkang 2014年4月10日
 * 
 */
public class Count {

	private int feed_count;
	private int follower_count;
	private int following_count;
	private int new_folower_count;
	private int unread_count;
	private int weibo_count;

	public int getFeed_count() {
		return feed_count;
	}

	public void setFeed_count(int feed_count) {
		this.feed_count = feed_count;
	}

	public int getFollower_count() {
		return follower_count;
	}

	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}

	public int getFollowing_count() {
		return following_count;
	}

	public void setFollowing_count(int following_count) {
		this.following_count = following_count;
	}

	public int getNew_folower_count() {
		return new_folower_count;
	}

	public void setNew_folower_count(int new_folower_count) {
		this.new_folower_count = new_folower_count;
	}

	public int getUnread_count() {
		return unread_count;
	}

	public void setUnread_count(int unread_count) {
		this.unread_count = unread_count;
	}

	public int getWeibo_count() {
		return weibo_count;
	}

	public void setWeibo_count(int weibo_count) {
		this.weibo_count = weibo_count;
	}

	@Override
	public String toString() {
		return "Count [feed_count=" + feed_count + ", follower_count="
				+ follower_count + ", following_count=" + following_count
				+ ", new_folower_count=" + new_folower_count
				+ ", unread_count=" + unread_count + ", weibo_count="
				+ weibo_count + "]";
	}
}
