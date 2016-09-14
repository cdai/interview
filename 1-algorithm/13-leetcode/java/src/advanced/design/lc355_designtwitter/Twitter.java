package advanced.design.lc355_designtwitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 */
public class Twitter {

    /* Own tweets */
    private Map<Integer,Set<Tweet>> tweets; // error5: critical since all timelines could have others' tweet...

    /* Timeline cached in Redis */
    private Map<Integer,Set<Tweet>> timelines;

    /* Relations stored in social graph DB */
    private Map<Integer,Set<Integer>> relations;

    /** Initialize your data structure here. */
    public Twitter() {
        this.tweets = new HashMap<>();
        this.timelines = new HashMap<>();
        this.relations = new HashMap<>();
    }

    // O(M*NlogN) time - M=#follower, N=#tweet
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tw = new Tweet(tweetId);
        if (getTweets(userId).add(tw)) {
            getTimeline(userId).add(tw);
            for (int follower : getFollower(userId))
                getTimeline(follower).add(tw);
        }
    }

    // O(N) time
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ret = new ArrayList<>();
        int size = 0;
        for (Tweet tw : getTimeline(userId)) {
            if (++size > 10) break;     // error4: forget...
            ret.add(tw.id);
        }
        return ret;
    }

    // O(M*NlogN) - M=#tweet
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        if (getFollower(followeeId).add(followerId))
            getTimeline(followerId).addAll(getTweets(followeeId));
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // error3: follow/unfollow itself
        getFollower(followeeId).remove(followerId);
        getTimeline(followerId).removeAll(getTweets(followeeId));
    }

    private Set<Tweet> getTweets(int userId) {
        if (!tweets.containsKey(userId))
            tweets.put(userId, new TreeSet<>((a, b) -> Long.compare(b.ts, a.ts)));
        return tweets.get(userId);
    }

    private Set<Tweet> getTimeline(int userId) {
        if (!timelines.containsKey(userId))
            timelines.put(userId, new TreeSet<>((a, b) -> Long.compare(b.ts, a.ts))); // error1: descending order!
        return timelines.get(userId);
    }

    private Set<Integer> getFollower(int followeeId) {
        if (!relations.containsKey(followeeId))
            relations.put(followeeId, new HashSet<>());
        return relations.get(followeeId);
    }

    class Tweet {
        int id; long ts = System.nanoTime(); // error2: currentTimeMillis() could be same if too fast!
        Tweet(int tweetId) { this.id = tweetId; }
    }

}
