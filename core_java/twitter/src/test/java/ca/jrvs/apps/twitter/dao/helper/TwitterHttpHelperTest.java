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
        String consumerKey = "Sm8X6bFzTwIl9ZWG1KfaRk98M";
        String consumerSecret = "mrPeOutTETb9YSN6t9p6w7lNqwOahKlKD3pCVFtE7HgeosFJrD";
        String accessToken = "1390311863884333056-egIfg3RZCs1iJZwCV0jY8uwVADsI1f";
        String tokenSecret = "dWuuWGxMFqHHGeityhEn1NGULFkuuhUxZf6qQhF8NGiOn";

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