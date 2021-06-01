package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.service.*;
import oauth.signpost.exception.*;
import org.apache.http.*;
import org.springframework.beans.factory.annotation.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private final Service service;
    private Tweet tweet;
    Double lon = null;
    Double lat = null;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }

    public static void main(String[] args) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        // Insert key info
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        HttpResponse response = newHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello_World_from_Java_App_2"));
    }

    /**
     * Parse user argument and post a tweet by calling service classes
     *
     * @param args
     * @return a posted tweet
     * @throws IllegalArgumentException if args are invalid
     */
    @Override
    public Tweet postTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid amount of arguments!");
        }
        tweet = TweetUtil.tweetUtil(args[1], lon, lat);
        return service.postTweet(tweet);
    }

    /**
     * Parse user argument and search a tweet by calling service classes
     *
     * @param args
     * @return a tweet
     * @throws IllegalArgumentException if args are invalid
     */
    @Override
    public Tweet showTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid amount of arguments!");
        }
        return service.showTweet(args[1], null);
    }

    /**
     * Parse user argument and delete tweets by calling service classes
     *
     * @param args
     * @return a list of deleted tweets
     * @throws IllegalArgumentException if args are invalid
     */
    @Override
    public List<Tweet> deleteTweet(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid amount of arguments!");
        }
        return service.deleteTweets(args[1].split(COMMA));
    }
}