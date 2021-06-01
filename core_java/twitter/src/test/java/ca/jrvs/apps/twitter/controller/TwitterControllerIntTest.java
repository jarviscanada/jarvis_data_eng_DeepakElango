package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.*;
import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.service.*;
import oauth.signpost.exception.*;
import org.junit.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {

    private TwitterController twitterController;
    private TwitterService twitterService;
    private Tweet test;
    Double lat = 1d;
    Double lon = -1d;
    String hashTag = "#Test_Case: ";
    String text = "@someonesometext " + hashTag + " " + System.currentTimeMillis();

    @Before
    public void setUp() throws Exception {
        // Insert key info
        String consumerKey = null;
        String consumerSecret = null;
        String accessToken = null;
        String tokenSecret = null;
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        CrdDao<Tweet, String> dao = new TwitterDao(httpHelper);
        twitterService = new TwitterService(dao);
        twitterController = new TwitterController(twitterService);
    }

    @Test
    public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        twitterController.postTweet(new String[]{text, "0", "0"});
        assertEquals(test.getText(), text);
    }

    @Test
    public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        Tweet test1 = twitterController.showTweet(new String[]{text, "0", "0"});
        test = twitterService.postTweet(TweetUtil.tweetUtil(text, lat, lon));
        assertEquals(test.getText(), test1.getText());
    }

    @Test
    public void deleteTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        List<Tweet> deleteTweets = twitterController.deleteTweet(new String[]{"delete", test.getText()});
        assertEquals(deleteTweets.size(), 0);
    }
}