package miscellaneous.design.lc355_designtwitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * It's too hard to maintain a static news feed list in the case that follow and unfollow happens a lot...
 */
public class Twitter2 {

    private Map<Integer,Set<Integer>> followee;

    private Map<Integer,List<Integer>> tweets;

    /** Initialize your data structure here. */
    public Twitter2() {
        this.followee = new HashMap<>();
        this.tweets = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        doPost(userId, tweetId);

        Set<Integer> followers = followee.get(userId);
        if (followers != null) {            // error1: no followers causing NPE
            for (int followerId : followers) {
                doPost(followerId, tweetId);
            }
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> tweetIds = tweets.get(userId);
        if (tweetIds == null) {
            return new ArrayList<>();
        }
        return tweetIds.size() >= 10 ? tweetIds.subList(0, 10) : tweetIds;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followers = followee.get(followeeId);
        if (followers == null) {
            followers = new HashSet<>();
            followee.put(followeeId, followers);
        }
        followers.add(followerId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followers = followee.get(followeeId);
        if (followers != null) {
            followers.remove(followerId);

            List<Integer> followerTweets = tweets.get(followerId);
            List<Integer> followeeTweets = tweets.get(followeeId);
            if (followeeTweets != null && followerTweets != null) { // error3: delete followee tweets from follower's feed.
                for (int id : followeeTweets) {
                    followerTweets.remove((Integer) id);
                }
            }
        }
    }

    private void doPost(int userId, int tweetId) {
        List<Integer> tweetIds = tweets.get(userId);
        if (tweetIds == null) {
            tweetIds = new ArrayList<>();
            tweets.put(userId, tweetIds);
        }
        tweetIds.add(0, tweetId);           // error2: tweets must be ordered from most recent to least recent
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
