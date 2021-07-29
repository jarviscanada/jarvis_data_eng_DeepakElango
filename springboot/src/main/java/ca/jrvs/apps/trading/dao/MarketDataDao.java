package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private final Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private final HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    /**
     * Get an IexQuote (helper method which class findAllById)
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quote = (List<IexQuote>) findAllById(Collections.singletonList(ticker));

        if (quote.size() == 0) {
            return Optional.empty();
        } else if (quote.size() == 1) {
            iexQuote = Optional.of(quote.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }

        return iexQuote;
    }

    /**
     * Get quotes from IEX
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        List<IexQuote> iexQuotes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Add tickers to iexQuotes ArrayList
        for (String ticker : tickers) {
            String url = String.format(IEX_BATCH_URL, ticker);

            try {
                String executeUrl = executeHttpGet(url).orElseThrow(() -> new IllegalArgumentException("TICKER NOT VALID"));
                JSONObject quotesJSONFormat = new JSONObject(executeUrl);
                JSONObject json = quotesJSONFormat.getJSONObject(ticker);
                iexQuotes.add(objectMapper.readValue(json.get("quote").toString(), IexQuote.class));
            } catch (IOException e) {
                throw new DataRetrievalFailureException("Data Retrieval Failure");
            }
        }
        return iexQuotes;
    }

    /**
     * Execute a get and return http entity/body as a string
     * Tip: use EntityUtils.toString to process HTTP entity
     */
    private Optional<String> executeHttpGet(String url) throws IOException {
        CloseableHttpClient closeableHttpClient = getHttpClient();
        HttpUriRequest httpUriRequest = new HttpGet(url);
        HttpResponse httpResponse = closeableHttpClient.execute(httpUriRequest);
        return Optional.of(EntityUtils.toString(httpResponse.getEntity()));
    }

    /**
     * Borrow a HTTP client from the httpClientConnectionManager
     *
     * @return a httpClient
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                //prevents connectionManager shutdown when calling httpClient.close()
                .setConnectionManagerShared(true)
                .build();
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> S save(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }
}