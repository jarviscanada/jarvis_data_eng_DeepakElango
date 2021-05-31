package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.*;
import ca.jrvs.apps.twitter.model.*;
import ca.jrvs.apps.twitter.example.JsonParser;
import com.google.gdata.util.common.base.*;
import oauth.signpost.exception.*;
import org.apache.http.*;
import org.apache.http.util.*;
import org.springframework.beans.factory.annotation.*;
import java.io.*;
import java.net.*;

public class TwitterDao implements CrdDao<Tweet, String> {

    //URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy/";
    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response code
    private static final int HTTP_OK = 200;

    private final HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    /**
     * Create an entity(Tweet) to the underlying storage
     *
     * @param entity entity that to be created
     * @return created entity
     */
    @Override
    public Tweet create(Tweet entity) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        URI uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper
                .escape(entity.getText()) + AMPERSAND + "long" + EQUAL + entity.getCoordinates()
                .getCoordinates().get(0) + AMPERSAND + "lat" + EQUAL + entity.getCoordinates()
                .getCoordinates().get(1));
        return validateBody(httpHelper.httpPost(uri));
    }

    /**
     * Find an entity(Tweet) by its id
     *
     * @param id entity id
     * @return Tweet entity
     */
    @Override
    public Tweet findById(String id) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        URI uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
        return validateBody(httpHelper.httpPost(uri));
    }

    /**
     * Delete an entity(Tweet) by its ID
     *
     * @param del of the entity to be deleted
     * @return deleted entity
     */
    @Override
    public Tweet deleteById(String[] del) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        URI uri = new URI(API_BASE_URI + DELETE_PATH + "/" + del + ".json");
        return validateBody(httpHelper.httpPost(uri));
    }

    public Tweet validateBody(HttpResponse response) {
        Tweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if (status != TwitterDao.HTTP_OK) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                System.out.println(("Response has no entity"));
            }
            throw new RuntimeException("unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        //convert response entity to str
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to string", e);
        }

        //Convert JSON string to Tweet object
        try {
            tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
            System.out.println(tweet);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert Json str to object", e);
        }
        return tweet;
    }
}
