package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.controller.*;
import ca.jrvs.apps.twitter.dao.*;
import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.model.*;
import oauth.signpost.exception.*;
import org.junit.*;

import java.io.*;
import java.net.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterServiceIntTest {

    private TwitterService twitterService;
    private Tweet test;

    @Before
    public void setUp() throws Exception {
        // Insert key info
        String consumerKey = null;
        String consumerSecret = null;
        String accessToken = null;
        String tokenSecret = null;
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        CrdDao<Tweet, String> twitterDao = new TwitterDao(httpHelper);
        twitterService = new TwitterService(twitterDao);
    }

    @Test
    public void postTweet() {
        Double lat = 1d;
        Double lon = -1d;
        assertNotNull(test);
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Double lat = 1d;
        Double lon = -1d;
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
       // twitterService.showTweet(test.getId_str(),null);
        assertNotNull(twitterService);
    }

    @Test
    public void deleteTweets() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Double lat = 1d;
        Double lon = -1d;
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
       // String[] ids = {test.getId_str()};
        //List<Tweet> tests = twitterService.deleteTweets(ids);
        //assertEquals(tests.get(0), test);
    }
}