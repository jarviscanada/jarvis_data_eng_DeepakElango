package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.*;
import oauth.signpost.commonshttp.*;
import oauth.signpost.exception.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.springframework.http.*;

import java.io.*;
import java.net.*;

public class TwitterHttpHelper implements HttpHelper {

    private final OAuthConsumer consumer;
    private final HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);
        httpClient = new DefaultHttpClient();
    }

    /**
     * /**
     * Execute a HTTP Post call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpPost(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        return executeRequest(HttpMethod.POST, uri);
    }

    /**
     * Execute a HTTP Get call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpGet(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        return executeRequest(HttpMethod.GET, uri);
    }

    private HttpResponse executeRequest(HttpMethod method, URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {
        if (method == HttpMethod.GET) {
            HttpGet getRequest = new HttpGet(uri);
            return httpClient.execute((HttpUriRequest) consumer.sign(getRequest));
        } else if (method == HttpMethod.POST) {
            HttpPost postRequest = new HttpPost(uri);
            return httpClient.execute((HttpUriRequest) consumer.sign(postRequest));
        } else {
            throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
        }
    }
}
