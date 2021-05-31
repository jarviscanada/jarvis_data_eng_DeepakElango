package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import oauth.signpost.exception.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.io.*;
import java.net.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;
    Tweet test = new Tweet();

    @Test
    public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Double lat = 1d;
        Double lon = -1d;
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        //when(dao.create(any())).thenReturn(new Tweet());
        //service.postTweet(TweetUtil.tweetUtil("test", 1d, -1d));
    }

    @Test
    public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Double lat = 1d;
        Double lon = -1d;
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        //when(dao.findById(any())).thenReturn(new Tweet());
        //service.showTweet(test.getId_str(), null);
    }

    @Test
    public void deleteTweets() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        Double lat = 1d;
        Double lon = -1d;
        assertEquals(lon, test.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, test.getCoordinates().getCoordinates().get(1));
        //when(dao.deleteById(any())).thenReturn(new Tweet());
        //service.deleteTweets(test.getId_str());
    }
}