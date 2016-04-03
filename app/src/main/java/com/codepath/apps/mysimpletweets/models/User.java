package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    // list attributes
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;


    public String getName() { return name; }
    public long getUid() { return uid; }
    public String getScreenName() { return screenName; }
    public String getProfileImageUrl() { return profileImageUrl; }

    // deserialize the JSON user => java User
    public static User fromJSON(JSONObject jsonObject) {
        User user = new User();
        // Extract and fill the values
        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.screenName = jsonObject.getString("screen_name");
            user.profileImageUrl = jsonObject.getString("profile_image_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Return a user
        return user;
    }
}
