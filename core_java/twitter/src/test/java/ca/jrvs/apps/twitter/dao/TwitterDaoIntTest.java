package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import oauth.signpost.exception.*;
import org.junit.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {
    private TwitterDao twitterDaoIntTest;
    private Tweet test;

    @Before
    public void setUp() throws Exception {
        // Insert key info
        String consumerKey = null;
        String consumerSecret = null;
        String accessToken = null;
        String tokenSecret = null;
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        twitterDaoIntTest = new TwitterDao(httpHelper);
    }

    @Test
    public void create() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        String hashTag = "#Test_Case: ";
        String text = "@someonesometext " + hashTag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;
        Tweet postTweet = TweetUtil.tweetUtil(text, lon, lat);
        Tweet tweet = twitterDaoIntTest.create(postTweet);

        assertEquals(text, tweet.getText());
        assertNotNull(text, tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
        assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
    }

    @Test
    public void findById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Tweet idTweet = twitterDaoIntTest.findById(test.getId_str());
        assertEquals(test, idTweet);
    }

    @Test
    public void deleteById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
       // Tweet delTweet = twitterDaoIntTest.deleteById(test.getId_str());
        //assertEquals(test, delTweet);
    }
}