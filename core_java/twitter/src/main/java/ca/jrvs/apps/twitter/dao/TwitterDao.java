package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.*;
import org.springframework.beans.factory.annotation.*;

public class TwitterDao implements CrdDao {

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

    private HttpHelper httpHelper;

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
    public Object create(Object entity) {
        return null;
    }

    /**
     * Find an entity(Tweet) by its id
     *
     * @param o entity id
     * @return Tweet entity
     */
    @Override
    public Object findById(Object o) {
        return null;
    }

    /**
     * Delete an entity(Tweet) by its ID
     *
     * @param o of the entity to be deleted
     * @return deleted entity
     */
    @Override
    public Object deleteById(Object o) {
        return null;
    }
}