package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.*;
import oauth.signpost.commonshttp.*;
import oauth.signpost.exception.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
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
        // Insert key info

        String consumerKey = "Sm8X6bFzTwIl9ZWG1KfaRk98M";
        String consumerSecret = "mrPeOutTETb9YSN6t9p6w7lNqwOahKlKD3pCVFtE7HgeosFJrD";
        String accessToken = "1390311863884333056-egIfg3RZCs1iJZwCV0jY8uwVADsI1f";
        String tokenSecret = "dWuuWGxMFqHHGeityhEn1NGULFkuuhUxZf6qQhF8NGiOn";
        HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        HttpResponse response = newHelper
                .httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello_World_from_Java_App"));
    }
        /**  String consumerKey = null;
          String consumerSecret = null;
          String accessToken = null;
          String tokenSecret = null;
         HttpHelper newHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
         HttpResponse response = newHelper
         .httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=Hello_World_from_Java_App"));
         }
      **/
    /**
     * /**
     * Execute a HTTP Post call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpPost(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        return executeRequest(HttpMethod.POST, uri, null);
    }

    /**
     * Execute a HTTP Get call
     *
     * @param uri
     * @return
     */
    @Override
    public HttpResponse httpGet(URI uri) throws OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException {
        return executeRequest(HttpMethod.GET, uri, null);
    }

    private HttpResponse executeRequest(HttpMethod method, URI uri, StringEntity stringEntity) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {
        if (method == HttpMethod.GET) {
            HttpGet getRequest = new HttpGet(uri);
            consumer.sign(getRequest);
            return httpClient.execute((getRequest));
        } else if (method == HttpMethod.POST) {
            HttpPost postRequest = new HttpPost(uri);
            consumer.sign(postRequest);
            return httpClient.execute((postRequest));
        } else {
            throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
        }
    }
}