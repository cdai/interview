package advanced.design.lc355_designtwitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and
 * is able to see the 10 most recent tweets in the user's news feed.
 * Your design should support the following methods:
 * 1.postTweet(userId, tweetId): Compose a new tweet.
 * 2.getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
 *  Each item in the news feed must be posted by users who the user followed or by the user herself.
 *  Tweets must be ordered from most recent to least recent.
 * 3.follow(followerId, followeeId): Follower follows a followee.
 * 4.unfollow(followerId, followeeId): Follower unfollows a followee.
 */
public class Twitter1 {

    private Map<Integer,Set<Integer>> follows;

    private Map<Integer,List<int[]>> tweets;

    private java.util.concurrent.atomic.AtomicInteger idGen;

    /** Initialize your data structure here. */
    public Twitter1() {
        this.follows = new HashMap<>();
        this.tweets = new HashMap<>();
        this.idGen = new java.util.concurrent.atomic.AtomicInteger();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<int[]> tweetIds = tweets.get(userId);
        if (tweetIds == null) {
            tweetIds = new ArrayList<>();
            tweets.put(userId, tweetIds);
        }
        tweetIds.add(0, new int[]{tweetId, idGen.incrementAndGet()});
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> top10 = new ArrayList<>();
        Set<Integer> followees = follows.get(userId);
        if (followees == null || followees.isEmpty()) {
            List<int[]> selfTweets = tweets.get(userId);
            if (selfTweets != null) {
                int size = Math.min(10, selfTweets.size());
                for (int i = 0; i < size; i++) {
                    top10.add(selfTweets.get(i)[0]);
                }
            }
            return top10; // error2: don't return null
        }

        // 1.Prepare candidate list and index record
        int n = followees.size() + 1;
        int[] index = new int[n];
        List<int[]>[] followeeTweets = new List[n];

        int i = 0;
        for (int followee : followees) {
            followeeTweets[i++] = tweets.get(followee);
        }
        followeeTweets[i] = tweets.get(userId);

        // 2.Retrieve top 10 most recent feeds
        for (i = 0; i < 10; i++) {
            int mostRecent = Integer.MIN_VALUE, mostIdx = 0;
            for (int j = 0; j < n; j++) {
                if (followeeTweets[j] != null
                        && index[j] < followeeTweets[j].size()              // error3: don't forget!
                        && followeeTweets[j].get(index[j])[1] > mostRecent) { // error1: bigger tweet id means more recent
                    mostRecent = followeeTweets[j].get(index[j])[1];
                    mostIdx = j;
                }
            }
            if (mostRecent == Integer.MIN_VALUE) {
                break;
            }
            top10.add(followeeTweets[mostIdx].get(index[mostIdx])[0]);
            index[mostIdx]++;
        }
        return top10;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) { // error4: what a...
            return;
        }

        Set<Integer> followees = follows.get(followerId);
        if (followees == null) {
            followees = new HashSet<>();
            follows.put(followerId, followees);
        }
        followees.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followees = follows.get(followerId);
        if (followees != null) {
            followees.remove(followeeId);
        }
    }

}
