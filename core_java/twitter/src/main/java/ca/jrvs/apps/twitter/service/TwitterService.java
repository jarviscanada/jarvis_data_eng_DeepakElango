package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.*;
import ca.jrvs.apps.twitter.model.*;
import oauth.signpost.exception.*;
import org.springframework.beans.factory.annotation.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class TwitterService implements Service{

    private CrdDao<Tweet, String> dao;
    @Autowired
    public TwitterService(CrdDao<Tweet, String> dao) {
        this.dao = dao;
    }
    private void validatePostTweet(Tweet tweet) throws IllegalArgumentException {
        if (tweet.getText().length() > 140) {
            throw new IllegalArgumentException("The maximum input cannot exceed 140 characters");
        } else if (tweet.getCoordinates().getCoordinates().get(0) > 180 || tweet.getCoordinates().getCoordinates().get(0) < -180 || tweet.getCoordinates().getCoordinates().get(1) > 90 || tweet.getCoordinates().getCoordinates().get(0) < -90) {
            throw new IllegalArgumentException("Longitude/Latitude not within boundary");
        }
    }

    /**
     * Validate and post a user input Tweet
     */
    @Override
    public Tweet postTweet(Tweet tweet) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        validatePostTweet(tweet);
        return dao.create(tweet);
    }

    /**
     * Search a tweet by ID
     */
    @Override
    public Tweet showTweet(String id, String[] fields) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        return dao.findById(id);
    }

    /**
     * Delete Tweet(s) by id(s).
     */
    @Override
    public List<Tweet> deleteTweets(String[] ids) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        List<Tweet> deleteTweets = new ArrayList<>();
        for (String element : ids) {
            deleteTweets.add(dao.deleteById(ids));
        }
        return deleteTweets;
    }
}
