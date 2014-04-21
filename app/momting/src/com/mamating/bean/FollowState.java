package com.mamating.bean;

/**
 * 关注状态
 * 
 * @author malinkang 2014年4月10日
 * 
 *         following 1 follower 0 关注的人 following 0 follower 1 粉丝
 * 
 *         following 0 follower 0 粉丝 following 1 follower 1 互相关注
 * 
 *         following -1 follower -1 自己
 */
public class FollowState {
	private int following;
	private int follower;

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	@Override
	public String toString() {
		return "FollowState [following=" + following + ", follower=" + follower
				+ "]";
	}

}
