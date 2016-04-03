package com.codepath.apps.mysimpletweets.models;

import android.net.ParseException;
import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

// Parse the JSON + Store the data, encapsulate state logic or display logic
public class Tweet {
    // list out the attributes
    private String body;
    private Long uid; // unique id for the tweet
    private User user; // store embedded user object
    private String createdAt;
    private String timeStamp;

    // Getter for attributes
    public String getBody() { return body; }
    public Long getUid() { return uid; }
    public String getCreatedAt() { return createdAt; }
    public User getUser() { return user; }
    public String getTimeStamp() { return timeStamp; }

    // deserialize the JSON and build Tweet objects
    // Tweet.fromJSON("{ ... }") => <Tweet>
    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        // Extract the values from the json, store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.timeStamp = tweet.getRelativeTimeAgo(jsonObject.getString("created_at"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return the tweet object
        return tweet;
    }

    // Tweet.fromJSONArray([{ ... }, { ... }, ... ]) => List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        // Iterate the jsonArray and create tweets
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonTweet = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(jsonTweet);
                if (tweet != null) { // if a tweet is successfully parsed
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue; // if one tweet doesn't work, continue to process other tweets
            }

        }
        // Return the finished list
        return tweets;
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
