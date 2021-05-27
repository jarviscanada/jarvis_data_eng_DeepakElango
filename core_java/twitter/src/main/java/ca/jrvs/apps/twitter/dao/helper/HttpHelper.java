package ca.jrvs.apps.twitter.dao.helper;

import java.io.*;
import java.net.URI;

import oauth.signpost.exception.*;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

    /**
     * Execute a HTTP Post call
     * @param uri
     * @return
     */
    HttpResponse httpPost(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException;

    /**
     * Execute a HTTP Get call
     * @param uri
     * @return
     */
    HttpResponse httpGet(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException;
}