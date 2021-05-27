package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.exception.*;
import org.apache.http.client.methods.*;
import org.junit.*;

import java.io.*;
import java.net.*;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    HttpHelper twitterHttpHelper;

    @Before
    public void setUp() throws Exception {
       // Insert key info
        this.twitterHttpHelper = new TwitterHttpHelper( consumerKey, consumerSecret, accessToken, tokenSecret);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void httpPost() throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        HttpPost postRequest = (HttpPost) twitterHttpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello World from Java App: Test"));
    }

    @Test
    public void httpGet() throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        HttpPost getRequest = (HttpPost) twitterHttpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1397576273434644482"));

    }
}