package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import oauth.signpost.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;

@Component
public class TwitterCLIApp {

    public static final String USAGE = "USAGE: TwitterCLI post|show|delete [options]";

    private final TwitterController controller;

    @Autowired
    public TwitterCLIApp(TwitterController controller) {
        this.controller = controller;
    }

    public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        CrdDao<Tweet, String> dao = new TwitterDao(helper);
        Service service = new TwitterService(dao);
        TwitterController controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);
        app.run(args);
    }

    public void run(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, URISyntaxException, OAuthCommunicationException {
        if (args.length == 0) {
            throw new IllegalArgumentException(USAGE);
        }
        switch (args[0].toLowerCase()) {
            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException(USAGE);
        }
    }

    public void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonParser.toJson(tweet, true, false));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert Tweet object to string", e);
        }
    }
}