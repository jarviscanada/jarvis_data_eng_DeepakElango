package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private final MarketDataDao marketDataDao;
  private final QuoteDao quoteDao;
  private static Quote savedQuote = new Quote();

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Find an IexQuote
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao
        .findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException("Argument is invalid"));
  }

  /**
   * Update quote table against IEX source -get all quotes from the db - foreach ticker get iexQuote
   * - convert iexQuote to quote entity - persist quote to db
   */
  public void updateMarketData() {
    List<Quote> listOfQuotes = findAllQuotes();
    for (Quote quote : listOfQuotes){
      String ticker = quote.getTicker();
      List<IexQuote> iexQuote = marketDataDao.findAllById(Collections.singleton(ticker));
      Iterable<Quote> buildQuoteFromIEX = iexQuote.stream().map(QuoteService::buildQuoteFromIexQuote).collect(Collectors.toList());
      quoteDao.save((Quote) buildQuoteFromIEX);
    }
  }

  /**
   * Helper method. Map a IexQuote to a Quote entity. Note: `IexQuote.getLatestPrice() == null` if
   * the stock market is closed. Set a default value for number field(s).
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    savedQuote.setTicker(iexQuote.getSymbol());
    savedQuote.setAskPrice(iexQuote.getIexAskPrice());
    savedQuote.setBidPrice(iexQuote.getIexBidPrice());
    savedQuote.setBidSize(Math.toIntExact(iexQuote.getIexBidSize()));
    savedQuote.setAskSize(Math.toIntExact(iexQuote.getIexAskSize()));
    savedQuote.setLastPrice(iexQuote.getLatestPrice());
    return savedQuote;
  }

  /**
   * Validate (against IEX) and save given tickers to quote table. - Get IexQuote(s) - convert each
   * IexQuote to Quote entity - persist the quote to db
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<Quote> quotes = new ArrayList<>();
    for (String ticker : tickers){
      quotes.add(saveQuote(ticker));
    }
    return quotes;
  }

  /** Helper method */
  public Quote saveQuote(String ticker) {
    Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
    savedQuote= buildQuoteFromIexQuote(iexQuote.orElseThrow(() -> new IllegalArgumentException("Argument is invalid")));
    return quoteDao.save(savedQuote);
  }

  /** Update a given quote to quote table without validation */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /** Find all quotes from the quote table */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}
