package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import oauth.signpost.exception.*;
import org.apache.http.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

import java.io.*;
import java.net.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception{
        String hashTag = "#Test_2: ";
        String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;
        //exception is expected here
        when(mockHelper.httpPost(isNotNull()));
        try {
            dao.create(TweetUtil.tweetUtil(text, lon, lat));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "        \"hashtags\":[],\n"
                + "        \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorite\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).validateBody((HttpResponse) expectedTweet);
        Tweet tweet = spyDao.create(TweetUtil.tweetUtil(text, lon, lat));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void findById() throws IOException, OAuthMessageSignerException, OAuthExpectationFailedException, URISyntaxException, OAuthCommunicationException {
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "        \"hashtags\":[],\n"
                + "        \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorite\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).validateBody((HttpResponse) expectedTweet);
        Tweet tweet = spyDao.findById(expectedTweet.getId_str());
        assertNotNull(tweet);
        assertNotNull(tweet.getText());

    }

    @Test
    public void deleteById() throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException {
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "        \"hashtags\":[],\n"
                + "        \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorite\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).validateBody((HttpResponse) expectedTweet);
        Tweet tweet = spyDao.deleteById(expectedTweet.getId_str());
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }
}