package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.*;
import ca.jrvs.apps.twitter.model.*;
import org.junit.*;
import org.mockito.*;

import static org.junit.Assert.*;

public class TwitterRestDaoUnitTest {

    @Test
    public void showTweet() throws Exception{
        String hashTag = "#abc";
        String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
        Double lat = 1d;
        Double lon = -1d;
        //exception is expected here
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TweetUtil.buildTweet(text, lon, lat));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
        //Test happy path
        //however, we don't want to call parseResponseBody.
        //we will make a spyDao which can fake parseResponseBody return value
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, lon, lat));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }
    @Test
    public void create() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }
}