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

    public static void main(String[] args) throws URISyntaxException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        String consumerKey = "Sm8X6bFzTwIl9ZWG1KfaRk98M";
        String consumerSecret = "mrPeOutTETb9YSN6t9p6w7lNqwOahKlKD3pCVFtE7HgeosFJrD";
        String accessToken = "1390311863884333056-egIfg3RZCs1iJZwCV0jY8uwVADsI1f";
        String tokenSecret = "dWuuWGxMFqHHGeityhEn1NGULFkuuhUxZf6qQhF8NGiOn";

        HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        HttpPost postRequest = (HttpPost) newHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello World from Java App"));
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
