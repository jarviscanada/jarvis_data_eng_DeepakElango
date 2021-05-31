package ca.jrvs.apps.twitter.example;

import ca.jrvs.apps.twitter.model.*;

import java.util.*;

public class TweetUtil {

    public static Tweet tweetUtil(String text, Double lat, Double lon) {
        Tweet tweet = new Tweet();
        Coordinates coord = new Coordinates();
        tweet.setText(text);

        List<Double> coordList = new ArrayList<>();
        coordList.add(lon);
        coordList.add(lat);
        coord.setCoordinates(coordList);
        tweet.setCoordinates(coord);
        return tweet;
    }
}

