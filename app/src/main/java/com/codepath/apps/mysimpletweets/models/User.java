package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    // list attributes
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagLine;
    private int followersCount;
    private int followingsCount;


    public String getName() { return name; }
    public long getUid() { return uid; }
    public String getScreenName() { return screenName; }
    public String getProfileImageUrl() { return profileImageUrl; }
    public String getTagLine() { return tagLine; }
    public int getFollowersCount() { return followersCount; }
    public int getFriendsCount() { return followingsCount; }

    // deserialize the JSON user => java User
    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        // Extract and fill the values
        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.tagLine = jsonObject.getString("description");
            user.followersCount = jsonObject.getInt("followers_count");
            user.followingsCount = jsonObject.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return a user
        return user;
    }
}
