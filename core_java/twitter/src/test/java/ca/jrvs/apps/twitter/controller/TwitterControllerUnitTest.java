package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.service.*;
import oauth.signpost.exception.*;
import org.assertj.core.util.*;
import org.assertj.core.util.Arrays;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    @Mock
    CrdDao dao;

    @InjectMocks
    private TwitterController twitterController;
    TwitterService service;
    Tweet test = new Tweet();
    Double lat = 1d;
    Double lon = -1d;
    String hashTag = "#Test_Case: ";
    String text = "@someonesometext " + hashTag + " " + System.currentTimeMillis();

    @Test
    public void postTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        when(service.postTweet(any())).thenReturn(new Tweet());
        twitterController.postTweet(new String[]{text, "0", "0"});
        assertEquals(test.getText(), text);
    }

    @Test
    public void showTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        when(service.showTweet(anyString(), any())).thenReturn(new Tweet());
        twitterController.showTweet(new String[]{test.getId_str()});
        assertEquals(test.getText(), text);
    }

    @Test
    public void deleteTweet() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        when(service.deleteTweets(any())).thenReturn((List<Tweet>) new Tweet());
        twitterController.deleteTweet(new String[]{test.getText()});
    }
}