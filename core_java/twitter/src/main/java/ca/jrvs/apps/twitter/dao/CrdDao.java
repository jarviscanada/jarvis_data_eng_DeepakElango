package ca.jrvs.apps.twitter.dao;

import oauth.signpost.exception.*;

import java.io.*;
import java.net.*;

public interface CrdDao<T, ID> {

    /**
     * Create an entity(Tweet) to the underlying storage
     * @param entity entity that to be created
     * @return created entity
     */
    T create(T entity) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException;

    /**
     * Find an entity(Tweet) by its id
     * @param id entity id
     * @return Tweet entity
     */
    T findById(String id) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException;

    /**
     * Delete an entity(Tweet) by its ID
     * @param id of the entity to be deleted
     * @return deleted entity
     */
    T deleteById(String[] id) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException, URISyntaxException;
}